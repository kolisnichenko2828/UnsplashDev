package com.staskokoc.unsplashdev.data.models

import com.staskokoc.unsplashdev.domain.models.UnsplashImage

data class UnsplashImagesRaw(
    val results: List<Result>
) {
    fun toUnsplashImages(): MutableList<UnsplashImage> {
        val images = mutableListOf<UnsplashImage>()
        for(result in results) {
            images.add(
                UnsplashImage(
                    smallImageUrl = result.urls.small,
                    fullImageUrl = result.urls.full,
                    authorName = result.user.name
                )
            )
        }

        return images
    }
}

data class Result(
    val urls: Urls,
    val user: User,
    val description: String,
    val slug: String
)

data class Urls(
    val full: String,
    val small: String
)

data class User(
    val name: String
)