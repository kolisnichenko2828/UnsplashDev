package com.staskokoc.unsplashdev.di

import com.staskokoc.unsplashdev.data.remote.UnsplashApi
import com.staskokoc.unsplashdev.data.repositories.UnsplashRepositoryImpl
import com.staskokoc.unsplashdev.domain.repositories.UnsplashRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val baseUrl = "https://api.unsplash.com"

val dataModule = module {

    single<UnsplashApi> {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        val service = retrofit.create(UnsplashApi::class.java)

        service
    }

    single<UnsplashRepository> {
        UnsplashRepositoryImpl(unsplashApi = get())
    }
}