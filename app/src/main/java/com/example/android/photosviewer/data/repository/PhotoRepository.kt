package com.example.android.photosviewer.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.photosviewer.data.database.getDatabase
import com.example.android.photosviewer.data.model.domain.Photo
import com.example.android.photosviewer.data.model.source.local.toDomainModel
import com.example.android.photosviewer.data.model.source.remote.toDatabaseModel
import com.example.android.photosviewer.network.PhotosWebService
import com.example.android.photosviewer.network.builder.RetrofitServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotoRepository(context: Context) {

    private val photosWebService = RetrofitServiceBuilder.buildService(PhotosWebService::class.java)

    private val database = getDatabase(context.applicationContext)

    val photos: LiveData<List<Photo>> = Transformations.map(database.photoDao.getAllPhotos()) {
        it.toDomainModel()
    }

    suspend fun getPhotos() {
        withContext(Dispatchers.IO) {
            val photosSearchNetworkResponse = photosWebService.searchPhotosAsync()
            database.photoDao.insertAll(*photosSearchNetworkResponse.photos.photos.toDatabaseModel())
        }
    }
}

