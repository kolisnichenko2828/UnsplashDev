package com.staskokoc.unsplashdev.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.staskokoc.unsplashdev.domain.models.UnsplashImage
import com.staskokoc.unsplashdev.domain.usecases.GetImageUsecase
import kotlinx.coroutines.launch

class MainViewModel(val getImagesUsecase: GetImageUsecase) : ViewModel() {
    val imagesLd = MutableLiveData<MutableList<UnsplashImage>>()
    val lastSearchLd = MutableLiveData<String>()
    val lastPositionLd = MutableLiveData<Int>()

    fun saveLastPosition(position: Int) {
        lastPositionLd.value = position
    }

    fun getImages(q: String) {
        lastSearchLd.value = q
        viewModelScope.launch {
            imagesLd.value = getImagesUsecase.execute(q = q)
        }
    }
}