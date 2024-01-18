package com.staskokoc.unsplashdev.data.remote

import com.staskokoc.unsplashdev.data.models.UnsplashImagesRaw
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {
    @GET("/search/photos")
    suspend fun getImagesRaw(
        @Query("client_id") client_id: String,
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): UnsplashImagesRaw
}