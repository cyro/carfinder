package com.autoscout24.carfinder.arch.data.database

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "vehicles")
class VehicleEntry {
    @PrimaryKey
    var id: Int = 0

    var make: String? = null
    var model: String? = null
    var fuel: String? = null
    var price: Double = 0.toDouble()
    var mileage: Double = 0.toDouble()
    var modelline: String? = null
    var firstRegistration: String? = null
    var color: String? = null
    var description: String? = null
    var images = ArrayList<Images>()

    @Embedded
    var seller: Seller? = null

    val makeAndModel: String
        @Ignore
        get() = if (make != null && model != null) {
            "$make $model"
        } else {
            "N/A"
        }

    //Assumption made here that id is unique and is returned from webservice, otherwise we could create a seperate constructor service call and autogenerate Id = true
    constructor(id: Int, make: String, model: String, fuel: String, price: Double, mileage: Double, modelline: String, firstRegistration: String, color: String, description: String, images: ArrayList<Images>, seller: Seller) {
        this.id = id
        this.make = make
        this.model = model
        this.fuel = fuel
        this.price = price
        this.mileage = mileage
        this.modelline = modelline
        this.firstRegistration = firstRegistration
        this.color = color
        this.description = description
        this.images = images
        this.seller = seller
    }

    constructor() {}

    //Convience string methods
    @Ignore
    override fun toString(): String {
        return "Vehicle [id = $id, model = $model, mileage = $mileage, price = $price, description = $description , fuel = $fuel, firstRegistration = $firstRegistration, make = $make]"
    }


}

