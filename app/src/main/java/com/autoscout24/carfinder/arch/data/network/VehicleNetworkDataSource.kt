package com.autoscout24.carfinder.arch.data.network

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.Intent
import android.util.Log
import com.autoscout24.carfinder.arch.core.AppExecutors
import com.autoscout24.carfinder.arch.data.database.VehicleEntry
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

class VehicleNetworkDataSource private constructor(private val mContext: Context, private val mExecutors: AppExecutors?) {

    var latestVehicles: MutableLiveData<Array<VehicleEntry>>

    init {
        latestVehicles = MutableLiveData()
    }


    fun startFetchVehicleListService() {
        var intentToFetchVehicles = Intent(mContext, VehicleSyncIntentService::class.java)
        mContext.startService(intentToFetchVehicles)
        Log.d(LOG_TAG, "Vehicle service created")
    }

    fun fetchVehicles() {
        Log.d(LOG_TAG, "Fetching vehicles")
        mExecutors?.networkIO()?.execute {
            try {
                val call = retrofitClient?.create(VehicleWebservice::class.java)?.getAllVehicles()
                call?.enqueue(object : Callback<Array<VehicleEntry>> {
                    override fun onResponse(call: Call<Array<VehicleEntry>>, response: Response<Array<VehicleEntry>>) {
                        Log.d(LOG_TAG, response.body()!!.toString())
                        latestVehicles.postValue(response.body())
                    }

                    override fun onFailure(call: Call<Array<VehicleEntry>>, t: Throwable) {
                        //Something went wrong
                        Log.d(LOG_TAG, "Something went wrong with your vehicle data call: " + t.localizedMessage)
                    }
                })

            } catch (e: Exception) {
                //Network issue
                e.printStackTrace()
            }


        }
    }


    companion object {

        private val LOG_TAG = VehicleNetworkDataSource::class.java.simpleName

        // Again Singleton because its the one point of accessing the api we and dont want multiple sources, just bad
        // For Singleton instantiation
        private val LOCK = Any()
        private  var sInstance: VehicleNetworkDataSource? = null

        val BASE_URL = "http://private-fe87c-simpleclassifieds.apiary-mock.com"
        //public static final String BASE_URL = "http://5b22e0afb968350014dd0043.mockapi.io/vehicles/";

        private var retrofit: Retrofit? = null

        fun getInstance(context: Context, executors: AppExecutors?): VehicleNetworkDataSource? {
            Log.d(LOG_TAG, "Getting the vehicle network data source")
            if (sInstance == null) {
                synchronized(LOCK) {
                    sInstance = VehicleNetworkDataSource(context.applicationContext, executors)
                    Log.d(LOG_TAG, "Made new vehicle network data source")
                }
            }
            return sInstance
        }

        //execute call back in background thread
        val retrofitClient: Retrofit?
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .callbackExecutor(Executors.newSingleThreadExecutor())
                            .build()
                }
                return retrofit
            }
    }
}