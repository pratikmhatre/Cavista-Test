package com.example.cavista_test.data.network

import com.example.cavista_test.data.network.responses.ImagesResponse
import io.reactivex.Single
import retrofit2.Response

interface ApiHelper {
    fun getImageList(query: String): Single<Response<ImagesResponse>>
}