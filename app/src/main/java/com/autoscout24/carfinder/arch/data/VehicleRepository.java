package com.autoscout24.carfinder.arch.data;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.autoscout24.carfinder.arch.core.AppExecutors;
import com.autoscout24.carfinder.arch.data.database.VehicleDao;
import com.autoscout24.carfinder.arch.data.database.VehicleEntry;
import com.autoscout24.carfinder.arch.data.network.VehicleNetworkDataSource;

import java.util.List;

public class VehicleRepository {

    private static final String LOG_TAG = VehicleRepository.class.getSimpleName();

    private static final Object LOCK = new Object();
    private static VehicleRepository sInstance;

    private final VehicleDao mVehicleDao;
    private final VehicleNetworkDataSource mVehicleDataSource;
    private final AppExecutors mExecutors;

    private boolean mIntialised = false;

    private VehicleRepository(VehicleDao mVehicleDao,
                             VehicleNetworkDataSource mVehicleDataSource,
                             AppExecutors mExecutors) {

        this.mVehicleDao = mVehicleDao;
        this.mVehicleDataSource = mVehicleDataSource;
        this.mExecutors = mExecutors;

        LiveData<VehicleEntry[]> networkData = mVehicleDataSource.getLatestVehicles();
        networkData.observeForever( newVehilcesFromNetwork -> {
            mExecutors.diskIO().execute(() -> {
                mVehicleDao.bulkInsert(newVehilcesFromNetwork);
                Log.d(LOG_TAG,"New vehicles inserted");
            });
        });

        //Load data if db has data
        mExecutors.diskIO().execute(() -> {
            if(isFetchRequired()) {
                Log.d(LOG_TAG,"Fetch is required");
                startFetchVehicleListService();
            }
        });
    }

    public synchronized static VehicleRepository getInstance(VehicleDao vehicleDao,
                                                             VehicleNetworkDataSource vehicleDataSource,
                                                             AppExecutors executors) {
        Log.d(LOG_TAG,"Fetching Repository");
        if(sInstance == null) {
            synchronized (LOCK) {
                sInstance = new VehicleRepository(vehicleDao,vehicleDataSource,executors);
                Log.d(LOG_TAG, "Created Vehicle Repository");
            }
        }
        return  sInstance;
    }

    //Intialize data
    private synchronized void initializeData() {
        //Check for intialization happens once per lifetime of app
        if(mIntialised) return;
        mIntialised = true;

        mExecutors.diskIO().execute(() -> {
            if(isFetchRequired()) {
                startFetchVehicleListService();
            }
        });
    }

    //Delete Old data

    //Check if a fetch is needed
    private boolean isFetchRequired() {
        int count = mVehicleDao.countAllVehicles();
        return (count <= 0);
    }

    //Start service
    private void startFetchVehicleListService() {
        mVehicleDataSource.startFetchVehicleListService();
    }

    public LiveData<List<VehicleEntry>> getAllVehicles() {
        //Lazy instantition
        initializeData();
        return mVehicleDao.getAllVehicles();
    }

}
