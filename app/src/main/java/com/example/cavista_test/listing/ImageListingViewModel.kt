package com.example.cavista_test.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.cavista_test.data.network.responses.ImagesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageListingViewModel(private val repository: ImageListingRepository) : ViewModel() {
    private val scope = CoroutineScope(Dispatchers.Default)
    val imageListLiveData: LiveData<ArrayList<ImagesResponse.Data.Image>> =
        Transformations.map(repository.imageData) {
            val tempList = ArrayList<ImagesResponse.Data.Image>()
            scope.launch {
                if (it.code() == 200 && it.body() != null) {
                    it.body()!!.data?.run {
                        this.map { item ->
                            if (!item.images.isNullOrEmpty()) {
                                tempList.add(item.images!!.first())
                            }
                        }
                    }
                }
            }
            return@map tempList
        }

    fun fetchResults(query: String) = repository.fetchImageResults(query)
}
