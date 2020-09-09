package com.example.cavista_test.utils

import com.example.cavista_test.data.network.responses.ImagesResponse

enum class Result { SUCCESS, FAILURE, AUTH_FAILURE, SERVER_ERROR }
class NetworkResponse<T:Any>(val result: Result, val data: T?)