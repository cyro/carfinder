package com.autoscout24.carfinder.arch.data.network;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.autoscout24.carfinder.arch.core.AppExecutors;
import com.autoscout24.carfinder.arch.data.database.VehicleEntry;

import java.io.IOException;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  Provides a single point to doing all operations with server
 */
public class VehicleNetworkDataSource {

    private static final String LOG_TAG = VehicleNetworkDataSource.class.getSimpleName();

    // Again Singleton because its the one point of accessing the api we and dont want multiple sources, just bad
    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static VehicleNetworkDataSource sInstance;
    private final Context mContext;

    private final AppExecutors mExecutors;

    //LiveData storing the latest downloaded Vehicles
    private final MutableLiveData<VehicleEntry[]> mDownloadedVehicle;

    public static final String BASE_URL = "http://private-fe87c-simpleclassifieds.apiary-mock.com";
    //public static final String BASE_URL = "http://5b22e0afb968350014dd0043.mockapi.io/vehicles/";

    private static Retrofit retrofit = null;

    private VehicleNetworkDataSource(Context context, AppExecutors executors) {
        mContext = context;
        mExecutors = executors;
        mDownloadedVehicle = new MutableLiveData<VehicleEntry[]>();
    }

    public static VehicleNetworkDataSource getInstance(Context context, AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the vehicle network data source");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new VehicleNetworkDataSource(context.getApplicationContext(), executors);
                Log.d(LOG_TAG, "Made new vehicle network data source");
            }
        }
        return sInstance;
    }

    public MutableLiveData<VehicleEntry[]> getLatestsVehicles() {
        return mDownloadedVehicle;
    }

    public void startFetchVehicleListService() {
        Intent intentToFetchVehicles = new Intent(mContext,VehicleSyncIntentService.class);
        mContext.startService(intentToFetchVehicles);
        Log.d(LOG_TAG,"Vehicle service created");
    }

    public void fetchVehicles() {
        Log.d(LOG_TAG,"Fetching vehicles");
        if(NetworkUtils.isNetworkAvailable(mContext)) {
            mExecutors.networkIO().execute(() -> {
                try {
                    Call<VehicleEntry[]> call = getRetrofitClient().create(VehicleWebservice.class).getAllVehicles();
                    call.enqueue(new Callback<VehicleEntry[]>() {
                        @Override
                        public void onResponse(Call<VehicleEntry[]> call, Response<VehicleEntry[]> response) {
                            Log.d(LOG_TAG, response.body().toString());
                            mDownloadedVehicle.postValue(response.body());
                        }

                        @Override
                        public void onFailure(Call<VehicleEntry[]> call, Throwable t) {
                            //Something went wrong
                            Log.d(LOG_TAG, "Something went wrong with your vehicle data call: " + t.getLocalizedMessage());
                        }
                    });

                } catch (Exception e) {
                    //Network issue
                    e.printStackTrace();
                }

            });
        }else {
            Toast.makeText(mContext,"No internet connection available",Toast.LENGTH_LONG).show();
        }
    }
    public static Retrofit getRetrofitClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    //execute call back in background thread
                    .callbackExecutor(Executors.newSingleThreadExecutor())
                    .build();
        }
        return retrofit;
    }
}
