package com.example.android.photosviewer.network

import com.example.android.photosviewer.BuildConfig
import com.example.android.photosviewer.data.model.source.remote.PhotosSearchNetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val SEARCH_PHOTOS_METHOD_QUERY_PARAM = "method"
private const val FORMAT_QUERY_PARAM = "format"
private const val NO_JSON_CALLBACK_QUERY_PARAM = "nojsoncallback"
private const val TEXT_QUERY_PARAM = "text"
private const val PAGE_QUERY_PARAM = "page"
private const val PER_PAGE_QUERY_PARAM = "per_page"
private const val API_KEY_QUERY_PARAM = "api_key"

interface PhotosWebService {

    @GET("?")
    suspend fun searchPhotosAsync(
        @Query(SEARCH_PHOTOS_METHOD_QUERY_PARAM) method: String = "flickr.photos.search",
        @Query(FORMAT_QUERY_PARAM) format: String = "json",
        @Query(NO_JSON_CALLBACK_QUERY_PARAM) noJsonCallback: Int = 50,
        @Query(TEXT_QUERY_PARAM) text: String = "Color",
        @Query(PAGE_QUERY_PARAM) page: Int = 1,
        @Query(PER_PAGE_QUERY_PARAM) perPage: Int = 20,
        @Query(API_KEY_QUERY_PARAM) apiKey: String = BuildConfig.FLICKR_API_KEY
    ): PhotosSearchNetworkResponse
}