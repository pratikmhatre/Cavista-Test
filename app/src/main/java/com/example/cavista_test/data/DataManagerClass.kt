package com.example.cavista_test.data

import androidx.lifecycle.LiveData
import com.example.cavista_test.data.db.DbHelperClass
import com.example.cavista_test.data.db.ImageComment
import com.example.cavista_test.data.network.ApiHelperClass
import javax.inject.Inject

class DataManagerClass @Inject constructor(
    var apiHelper: ApiHelperClass,
    val dbHelperClass: DbHelperClass
) : DataManager {
    override fun getImageList(query: String) = apiHelper.getImageList(query)
    override fun insertComment(imageComment: ImageComment) {
        dbHelperClass.insertComment(imageComment)
    }

    override fun getCommentsOfImage(imageId: String): LiveData<List<ImageComment>> {
        return dbHelperClass.getCommentsOfImage(imageId)
    }
}