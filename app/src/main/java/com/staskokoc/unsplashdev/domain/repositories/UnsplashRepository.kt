package com.staskokoc.unsplashdev.domain.repositories

import com.staskokoc.unsplashdev.domain.models.UnsplashImage

interface UnsplashRepository {
    suspend fun getImages(q: String): MutableList<UnsplashImage>
}