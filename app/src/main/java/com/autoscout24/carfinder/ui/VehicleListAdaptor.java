package com.autoscout24.carfinder.ui;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.autoscout24.carfinder.R;
import com.autoscout24.carfinder.arch.data.database.VehicleEntry;
import com.autoscout24.carfinder.ui.utils.MathUtils;
import com.autoscout24.carfinder.ui.utils.StringUtils;
import com.autoscout24.carfinder.ui.utils.VehicleViewPagerAdaptor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VehicleListAdaptor extends RecyclerView.Adapter {
    private static final String LOG_TAG = VehicleListAdaptor.class.getSimpleName();

    private static int POSITION_OF_ADVERTISMENTS = 3;

    private static final int VIEW_VEHICLE = 0;
    private static final int VIEW_ADVERTISEMENT = 1;
    private static final int VIEW_UNKNOWN_TYPE = -1;

    private List<VehicleAdvertisementWrapper> vehicleAdvertisementList;

    private Context mContext;

    public VehicleListAdaptor(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_VEHICLE) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_card_view, parent, false);
            return new VehicleListViewHolder(itemView);
        } else if (viewType == VIEW_ADVERTISEMENT) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.advertisment_card_view, parent, false);
            return new AdvertisementViewHolder(itemView);
        }
        else return null;
    }

    @Override
    public int getItemViewType(int position) {
        VehicleAdvertisementWrapper vehicleAdvertisementWrapper = vehicleAdvertisementList.get(position);
        if (vehicleAdvertisementWrapper.isVehicle()) {
            return VIEW_VEHICLE;
        } else if (vehicleAdvertisementWrapper.isAdvertisement()) {
            return VIEW_ADVERTISEMENT;
        } else {
            // Should never get here, but ,always good to code defensive
            Log.e(LOG_TAG, "HEY Coder... you have added a new Item view type that isnt available");
            return VIEW_UNKNOWN_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.setIsRecyclable(true);
        VehicleAdvertisementWrapper vehicleAdvertisementWrapper = vehicleAdvertisementList.get(position);

        if (vehicleAdvertisementWrapper.isVehicle() ) {
            Log.d(LOG_TAG,"vehicleAdvertisementWrapper.isVehicle() "+ position);
            VehicleListViewHolder viewHolder = (VehicleListViewHolder) holder;

            VehicleEntry vehicleEntry = vehicleAdvertisementWrapper.getVehicleEntry();
            viewHolder.vehicleFuelTypeTextView.setText(StringUtils.isNullorEmpty(vehicleEntry.getFuel()));
            viewHolder.vehicleMakeModelTextView.setText(StringUtils.isNullorEmpty(vehicleEntry.getMakeAndModel()));
            viewHolder.vehiclePriceTextView.setText(StringUtils.getAttributtedCurrency(vehicleEntry.getPrice()));
            viewHolder.vehicleMileageTextView.setText(StringUtils.getAttributedMileage(vehicleEntry.getMileage()));
            viewHolder.vehicleColorTextView.setText(StringUtils.isNullorEmpty(vehicleEntry.getColor()));
            viewHolder.vehicleSellerCityTextView.setText(vehicleEntry.getSeller() != null ? StringUtils.isNullorEmpty(vehicleEntry.getSeller().getCity()) : "N/A");
            viewHolder.vehicleSellerTellTextView.setText(vehicleEntry.getSeller() != null ? StringUtils.isNullorEmpty(vehicleEntry.getSeller().getPhone()) : "N/A");
            viewHolder.vehicleSellerTextView.setText(vehicleEntry.getSeller() != null ? StringUtils.isNullorEmpty(vehicleEntry.getSeller().getType()) : "N/A");
            viewHolder.vehicleDescriptionTextView.setText(StringUtils.isNullorEmpty(vehicleEntry.getDescription()));

            if (vehicleEntry.getImages() != null && !vehicleEntry.getImages().isEmpty()) {
                VehicleViewPagerAdaptor pagerAdaptor = new VehicleViewPagerAdaptor(vehicleEntry.getImages(), mContext);
                viewHolder.vehicleViewPager.setAdapter(pagerAdaptor);
                viewHolder.tabLayout.setupWithViewPager(viewHolder.vehicleViewPager, true);
                viewHolder.vehicleViewPager.setVisibility(View.VISIBLE);
                viewHolder.tabLayout.setVisibility(View.VISIBLE);
                viewHolder.noVehicleImageView.setVisibility(View.GONE);
            } else {
                //No point in displaying ViewPager
                viewHolder.vehicleViewPager.setVisibility(View.GONE);
                viewHolder.tabLayout.setVisibility(View.GONE);
                viewHolder.noVehicleImageView.setVisibility(View.VISIBLE);
            }

        } else if (vehicleAdvertisementWrapper.isAdvertisement()) {
            Log.d(LOG_TAG,"vehicleAdvertisementWrapper.isAdvertisement() "+ position);
            AdvertisementViewHolder viewHolder = (AdvertisementViewHolder) holder;
            viewHolder.advertismentImageView.setBackgroundResource(R.drawable.animate_advertisments);
            AnimationDrawable animationDrawable = (AnimationDrawable) viewHolder.advertismentImageView.getBackground();
            animationDrawable.start();
        }
    }

    @Override
    public int getItemCount() {
        return vehicleAdvertisementList.size();
    }

    public void setVehicleEntryList(List<VehicleEntry> vehicleEntryList) {
        vehicleAdvertisementList = new ArrayList<>();
        for (VehicleEntry vehicle : vehicleEntryList) {
            vehicleAdvertisementList.add(new VehicleAdvertisementWrapper(vehicle));
        }

        for(int i = 0 ;i< vehicleAdvertisementList.size();i++) {
            if(i % POSITION_OF_ADVERTISMENTS == 0) {
                vehicleAdvertisementList.add(i, new VehicleAdvertisementWrapper("Advertisement"));
                Log.d(LOG_TAG,"Advertisment");
            }
            else {
                Log.d(LOG_TAG,"Vehicle");
            }
        }
        //Adjusting for zero based index, because 3%0 =0
        if(vehicleAdvertisementList.size()>0)
        vehicleAdvertisementList.remove(0);
        notifyDataSetChanged();
    }

    static class VehicleListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.vehicleMakeModelTextView)
        TextView vehicleMakeModelTextView;
        @BindView(R.id.vehiclePriceTextView)
        TextView vehiclePriceTextView;
        @BindView(R.id.vehicleMileageTextView)
        TextView vehicleMileageTextView;
        @BindView(R.id.vehicleFuelTypeTextView)
        TextView vehicleFuelTypeTextView;
        @BindView(R.id.vehicleSellerTypeTextView)
        TextView vehicleSellerTextView;
        @BindView(R.id.vehicleSellerTellTextView)
        TextView vehicleSellerTellTextView;
        @BindView(R.id.vehicleSellerCityTextView)
        TextView vehicleSellerCityTextView;
        @BindView(R.id.vehicleColorTextView)
        TextView vehicleColorTextView;
        @BindView(R.id.vehicleDescriptionTextView)
        TextView vehicleDescriptionTextView;
        @BindView(R.id.vehicleViewPager)
        ViewPager vehicleViewPager;
        @BindView(R.id.noImagesAvailableImageView)
        ImageView noVehicleImageView;
        @BindView(R.id.tab_layout)
        TabLayout tabLayout;

        public VehicleListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class AdvertisementViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.advertismentImageView)
        ImageView advertismentImageView;

        public AdvertisementViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
