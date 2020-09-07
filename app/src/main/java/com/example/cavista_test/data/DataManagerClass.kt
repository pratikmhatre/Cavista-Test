package com.example.cavista_test.data

import com.example.cavista_test.data.network.ApiHelperClass
import javax.inject.Inject

class DataManagerClass @Inject constructor(var apiHelper: ApiHelperClass) : DataManager {
    override fun getImageList(query: String) = apiHelper.getImageList(query)
}