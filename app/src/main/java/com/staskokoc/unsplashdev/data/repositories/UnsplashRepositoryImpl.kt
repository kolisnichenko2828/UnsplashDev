package com.staskokoc.unsplashdev.data.repositories

import com.staskokoc.unsplashdev.data.remote.UnsplashApi
import com.staskokoc.unsplashdev.domain.models.UnsplashImage
import com.staskokoc.unsplashdev.domain.repositories.UnsplashRepository

class UnsplashRepositoryImpl(val unsplashApi: UnsplashApi): UnsplashRepository {
    private val client_id: String = "TNqa7zMa5zl-rqyvAPYosnSY7XsJURJtRVWSWyKvbZs"
    private var page: Int = 1
    private var per_page: Int = 30

    override suspend fun getImages(q: String): MutableList<UnsplashImage> {
        val imagesRaw = unsplashApi.getImagesRaw(
            client_id = client_id,
            query = q,
            page = page,
            per_page = per_page
        )
        val images = imagesRaw.toUnsplashImages()

        return images
    }
}