package com.example.cavista_test.data.network

import com.example.cavista_test.data.network.responses.ImagesResponse
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class ApiHelperClass @Inject constructor(var apiList: ApiList) : ApiHelper {
    override fun getImageList(query: String): Single<Response<ImagesResponse>> = apiList.getImageList(query)
}