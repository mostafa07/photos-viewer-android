package com.example.android.photosviewer.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.photosviewer.data.model.source.local.PhotoEntity

@Dao
interface PhotoDao {

    @Query("SELECT * FROM PHOTO")
    fun getAllPhotos(): LiveData<List<PhotoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg recipes: PhotoEntity)
}