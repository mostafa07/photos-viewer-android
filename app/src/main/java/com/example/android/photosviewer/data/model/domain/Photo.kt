package com.example.android.photosviewer.data.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    val isPublic: Int,
    val isFriend: Int,
    val isFamily: Int
) : Parcelable {

    fun getUrl(): String {
        return "https://farm${farm}.static.flickr.com/${server}/${id}_${secret}.jpg"
    }
}