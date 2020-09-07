package com.example.cavista_test.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ImageListingFactory @Inject constructor(val repository: ImageListingRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageListingViewModel::class.java)) {
            return ImageListingViewModel(repository) as T
        }
        throw  IllegalStateException()
    }

}