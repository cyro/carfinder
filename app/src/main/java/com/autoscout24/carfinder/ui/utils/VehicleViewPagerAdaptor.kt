package com.autoscout24.carfinder.ui.utils

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.autoscout24.carfinder.R
import com.autoscout24.carfinder.arch.data.database.Images
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class VehicleViewPagerAdaptor constructor(private val images: ArrayList<Images>, private val mContext: Context)
    : PagerAdapter() {
    val inflater: LayoutInflater = LayoutInflater.from(mContext)

    var imageView: ImageView? = null
    var textView: TextView? = null

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.vehicle_image_view, collection, false) as ViewGroup
        imageView = imageLayout.findViewById(R.id.vehicleImage)
        textView = imageLayout.findViewById(R.id.numberOfImagesTextView)
        textView?.let { textView?.setText(mContext.getString(R.string.numberIndicator, position + 1, images.size)) }
        val requestOptions = RequestOptions() as RequestOptions
        requestOptions.placeholder(R.drawable.placeholder_car)
        requestOptions.error(R.drawable.placeholder_error)
        requestOptions.centerCrop()

        Glide.with(mContext)
                .setDefaultRequestOptions(requestOptions)
                .load(images[position].url)
                .into(this.imageView!!)

        collection.addView(imageLayout)
        return imageLayout
    }

    //View Pager Methods
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }
}