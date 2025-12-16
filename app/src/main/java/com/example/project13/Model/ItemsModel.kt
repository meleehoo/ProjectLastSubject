package com.example.project13.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemsModel(
    var title : String ="",
    var description:String="",
    var picUrl: ArrayList<String> = ArrayList(),
    var size: ArrayList<String> = ArrayList(),
    var price: Double = 0.0,
    var rating: Double = 0.0,
    var numberInCart : Int = 0,
    var categoryId : Int = 0,
    var sellerName : String = "",
    var sellerTell : Int = 0,
    var sellerPic : String = ""
): Parcelable


