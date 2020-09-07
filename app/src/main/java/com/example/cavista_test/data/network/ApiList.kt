package com.example.cavista_test.data.network

import com.example.cavista_test.data.network.responses.ImagesResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiList {
    @GET("3/gallery/search/1")
    fun getImageList(@Query("q") query: String): Single<Response<ImagesResponse>>
}