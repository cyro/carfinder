package com.autoscout24.carfinder.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.autoscout24.carfinder.R;
import com.autoscout24.carfinder.arch.core.InjectorUtils;
import com.autoscout24.carfinder.arch.data.database.VehicleEntry;

public class VehicleListActivity extends AppCompatActivity {

    private VehicleListViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VehicleViewModelFactory factory = InjectorUtils.provideVehicleViewModelFactory(this.getApplicationContext());

        mViewModel = ViewModelProviders.of(this, factory).get(VehicleListViewModel.class);
        
        //Create obeservation for changes in Vehicles
        mViewModel.getVehicle().observe(this, vehicleEntry -> {
            //Any changes update to UI
            if(vehicleEntry != null) bindVehicleListToUI(vehicleEntry);
        });


    }

    private void bindVehicleListToUI(VehicleEntry vehicleEntry) {
    }
}
