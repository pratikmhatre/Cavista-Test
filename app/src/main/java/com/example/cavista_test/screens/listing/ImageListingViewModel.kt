package com.example.cavista_test.screens.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.cavista_test.data.network.responses.ImagesResponse
import com.example.cavista_test.utils.NetworkResponse
import com.example.cavista_test.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageListingViewModel(private val repository: ImageListingRepository) : ViewModel() {

    val imageListLiveData: LiveData<NetworkResponse<List<ImagesResponse.Data.Image>>> =
        Transformations.map(repository.imageData) {
            var finalResponse: NetworkResponse<List<ImagesResponse.Data.Image>>? = null
            if (it.result == Result.SUCCESS) {
                it.data?.run {
                    finalResponse = when (code()) {
                        200 -> {
                            val tempList = ArrayList<ImagesResponse.Data.Image>()
                            body()?.data?.apply {
                                this.map { item ->
                                    if (!item.images.isNullOrEmpty()) {
                                        //adding only first image from each result
                                        val image = item.images!!.first()
                                        image.title = item.title
                                        tempList.add(image)
                                    }
                                }
                            }
                            NetworkResponse(
                                result = Result.SUCCESS,
                                data = tempList
                            )
                        }
                        401 -> {
                            NetworkResponse(
                                result = Result.AUTH_FAILURE,
                                data = null
                            )
                        }
                        else -> {
                            NetworkResponse(
                                result = Result.SERVER_ERROR,
                                data = null
                            )
                        }
                    }
                }

            } else {
                finalResponse = NetworkResponse(
                    result = Result.FAILURE,
                    data = null
                )
            }
            return@map finalResponse
        }

    fun fetchResults(query: String) = repository.fetchImageResults(query)
}
