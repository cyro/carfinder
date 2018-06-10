package com.autoscout24.carfinder.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.autoscout24.carfinder.R;
import com.autoscout24.carfinder.arch.data.database.VehicleEntry;

import java.util.List;

public class VehicleListAdaptor extends RecyclerView.Adapter {

    private static final int VIEW_VEHICLE = 1;
    private static final int VIEW_ADVERTISEMENT = 2;

    private List<VehicleEntry> vehicleEntryList;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_VEHICLE) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_card_view, parent, false);
            return new VehicleListViewHolder(itemView);
        }else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.advertisment_card_view, parent, false);
            return new AdvertisementViewHolder(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int adjustedForZeroBasedPosition = position + 1;
        return adjustedForZeroBasedPosition % 3 == 0 ?  VIEW_ADVERTISEMENT : VIEW_VEHICLE;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof VehicleListViewHolder) {

        }else if(holder instanceof AdvertisementViewHolder) {

        }
    }

    @Override
    public int getItemCount() {
        return vehicleEntryList.size()+ mod(vehicleEntryList.size(),3) ;
    }

    private int mod(int x, int y)
    {
        int result = x % y;
        return result < 0? result + y : result;
    }

    public void setVehicleEntryList(List<VehicleEntry> vehicleEntryList) {
        this.vehicleEntryList = vehicleEntryList;
        notifyDataSetChanged();
    }

    static class VehicleListViewHolder extends RecyclerView.ViewHolder {

        public VehicleListViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class AdvertisementViewHolder extends RecyclerView.ViewHolder {

        public AdvertisementViewHolder(View itemView) {
            super(itemView);
        }
    }
}
