package com.staskokoc.unsplashdev.domain.usecases

import com.staskokoc.unsplashdev.domain.models.UnsplashImage
import com.staskokoc.unsplashdev.domain.repositories.UnsplashRepository

class GetImageUsecase(val unsplashRepository: UnsplashRepository) {
    suspend fun execute(q: String): MutableList<UnsplashImage> {
        val images = unsplashRepository.getImages(q = q)
        return images
    }
}