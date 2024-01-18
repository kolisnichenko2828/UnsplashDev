package com.staskokoc.unsplashdev.di

import com.staskokoc.unsplashdev.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MainViewModel> {
        MainViewModel(
            getImagesUsecase = get()
        )
    }
}