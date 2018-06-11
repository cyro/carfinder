package com.autoscout24.carfinder.ui.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.autoscout24.carfinder.R;
import com.autoscout24.carfinder.arch.data.database.Images;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VehicleViewPagerAdaptor extends PagerAdapter {

    private final LayoutInflater inflater;
    private final Context mContext;
    private ArrayList<Images> images;

    ImageView imageView;
    TextView textView;

    public VehicleViewPagerAdaptor(ArrayList<Images> images, Context mContext) {
        this.mContext = mContext;
        this.images = images;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        ViewGroup imageLayout = (ViewGroup) inflater.inflate(R.layout.vehicle_image_view, collection, false);
        imageView = imageLayout.findViewById(R.id.vehicleImage);
        textView = imageLayout.findViewById(R.id.numberOfImagesTextView);
        textView.setText(mContext.getString(R.string.numberIndicator,position+1,images.size()));
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.placeholder_car);
        requestOptions.error(R.drawable.placeholder_error);
        requestOptions.centerCrop();

        Glide.with(mContext)
                .setDefaultRequestOptions(requestOptions)
                .load(images.get(position).getUrl())
                .into(imageView);

        collection.addView(imageLayout);
        return imageLayout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
