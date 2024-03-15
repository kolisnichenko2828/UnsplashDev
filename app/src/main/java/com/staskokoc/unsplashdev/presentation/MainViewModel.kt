package com.staskokoc.unsplashdev.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.staskokoc.unsplashdev.domain.models.UnsplashImage
import com.staskokoc.unsplashdev.domain.usecases.GetImageUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(val getImagesUsecase: GetImageUsecase) : ViewModel() {
    val imagesLd = MutableStateFlow<MutableList<UnsplashImage>>(mutableListOf())
    val lastSearchLd = MutableLiveData<String>()
    val lastPositionLd = MutableLiveData<Int>()

    fun saveLastPosition(position: Int) {
        lastPositionLd.value = position
    }

    fun getImages(q: String) {
        lastSearchLd.value = q
        viewModelScope.launch {
            val response = getImagesUsecase.execute(q = q)
            imagesLd.emit(response)
            imagesLd.value = getImagesUsecase.execute(q = q)
        }
    }
}