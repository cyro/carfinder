package com.autoscout24.carfinder.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.RelativeLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.autoscout24.carfinder.R
import com.autoscout24.carfinder.arch.core.InjectorUtils
import com.autoscout24.carfinder.arch.data.database.VehicleEntry

class VehicleListActivity : AppCompatActivity() {
    var viewModel: VehicleListViewModel? = null

    @BindView(R.id.vehicleRecyclerView)
    internal lateinit var vehicleRecyclerView: RecyclerView

    @BindView(R.id.loadingLayout)
    internal lateinit var mLoadingLayout: RelativeLayout

    private var vehicleListAdaptor: VehicleListAdaptor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        vehicleListAdaptor = VehicleListAdaptor(this)
        vehicleRecyclerView.adapter = vehicleListAdaptor

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            vehicleRecyclerView.layoutManager = LinearLayoutManager(this)
        } else {
            val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            vehicleRecyclerView.layoutManager = staggeredGridLayoutManager
        }

        val factory = InjectorUtils.provideVehicleViewModelFactory(this.applicationContext)
        viewModel = ViewModelProviders.of(this, factory).get(VehicleListViewModel::class.java)

        //Create observation for changes in Vehicles
        viewModel?.vehicles?.observe(this, Observer { vehicleEntries ->
            if (vehicleEntries != null) bindVehicleListToUI(vehicleEntries)
        })
    }

    private fun bindVehicleListToUI(vechileList: List<VehicleEntry>) {
        mLoadingLayout.visibility = View.GONE
        vehicleRecyclerView.visibility = View.VISIBLE
        vehicleListAdaptor?.setVehicleEntryList(vechileList)
    }

    companion object {
        var LOG_TAG: String = VehicleListActivity::class.java.simpleName
    }

}