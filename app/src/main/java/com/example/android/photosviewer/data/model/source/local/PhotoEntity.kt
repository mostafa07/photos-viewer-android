package com.example.android.photosviewer.data.model.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.photosviewer.data.model.domain.Photo

@Entity(tableName = "PHOTO")
data class PhotoEntity(
    @PrimaryKey
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

fun List<PhotoEntity>.toDomainModel(): List<Photo> {
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