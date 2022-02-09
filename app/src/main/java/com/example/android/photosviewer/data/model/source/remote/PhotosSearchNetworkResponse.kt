package com.example.android.photosviewer.data.model.source.remote

import com.example.android.photosviewer.data.model.domain.Photo
import com.example.android.photosviewer.data.model.source.local.PhotoEntity
import com.google.gson.annotations.SerializedName

data class PhotosSearchNetworkResponse(
    val photos: Photos,
    @SerializedName("stat")
    val status: String
)

data class Photos(
    val page: Int,
    val pages: Int,
    @SerializedName("perpage")
    val perPage: Int,
    val total: Int,
    @SerializedName("photo")
    val photos: List<PhotoNetworkModel>
)

data class PhotoNetworkModel(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    val isPublic: Int,
    val isFriend: Int,
    val isFamily: Int
)


fun List<PhotoNetworkModel>.toDomainModel(): List<Photo> {
    return map {
        Photo(
            id = it.id,
            owner = it.owner,
            secret = it.secret,
            server = it.server,
            farm = it.farm,
            title = it.title,
            isPublic = it.isPublic,
            isFriend = it.isFriend,
            isFamily = it.isFamily
        )
    }
}

fun List<PhotoNetworkModel>.toDatabaseModel(): Array<PhotoEntity> {
    return map {
        PhotoEntity(
            id = it.id,
            owner = it.owner,
            secret = it.secret,
            server = it.server,
            farm = it.farm,
            title = it.title,
            isPublic = it.isPublic,
            isFriend = it.isFriend,
            isFamily = it.isFamily
        )
    }.toTypedArray()
}