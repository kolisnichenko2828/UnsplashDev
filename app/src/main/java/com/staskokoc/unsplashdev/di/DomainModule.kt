package com.staskokoc.unsplashdev.di

import com.staskokoc.unsplashdev.domain.usecases.GetImageUsecase
import org.koin.dsl.module

val domainModule = module {

    factory<GetImageUsecase> {
        GetImageUsecase(unsplashRepository = get())
    }
}