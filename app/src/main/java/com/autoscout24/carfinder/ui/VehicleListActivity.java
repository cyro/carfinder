package com.autoscout24.carfinder.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.autoscout24.carfinder.R;
import com.autoscout24.carfinder.arch.core.InjectorUtils;
import com.autoscout24.carfinder.arch.data.database.VehicleEntry;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VehicleListActivity extends AppCompatActivity {
    private static final String LOG_TAG = VehicleListActivity.class.getSimpleName();

    private VehicleListViewModel mViewModel;

    @BindView(R.id.vehicleRecyclerView)
    RecyclerView mVehicleRecyclerView;

    @BindView(R.id.loadingLayout)
    RelativeLayout mLoadingLayout;

    private VehicleListAdaptor mVehicleListAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mVehicleListAdaptor = new VehicleListAdaptor(this);
        mVehicleRecyclerView.setAdapter(mVehicleListAdaptor);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mVehicleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            StaggeredGridLayoutManager staggeredGridVertical = new
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            mVehicleRecyclerView.setLayoutManager(staggeredGridVertical);
        }

        VehicleViewModelFactory factory = InjectorUtils.provideVehicleViewModelFactory(this.getApplicationContext());

        mViewModel = ViewModelProviders.of(this, factory).get(VehicleListViewModel.class);

        //Create obeservation for changes in Vehicles
        mViewModel.getVehicles().observe(this, vehicleEntries -> {
            //Any changes update to UI
            if (vehicleEntries != null) bindVehicleListToUI(vehicleEntries);
        });
    }

    private void bindVehicleListToUI(List<VehicleEntry> vehicleList) {
        mLoadingLayout.setVisibility(View.GONE);
        mVehicleRecyclerView.setVisibility(View.VISIBLE);
        mVehicleListAdaptor.setVehicleEntryList(vehicleList);

    }
}
