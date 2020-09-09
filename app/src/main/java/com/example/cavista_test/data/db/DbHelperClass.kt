package com.example.cavista_test.data.db

import androidx.lifecycle.LiveData
import javax.inject.Inject

class DbHelperClass @Inject constructor(val myDatabase: MyDatabase) : DbHelper {
    override fun insertComment(imageComment: ImageComment) {
        val dao = myDatabase.getCommentsDao()
        dao.addComment(comment = imageComment)
    }

    override fun getCommentsOfImage(imageId: String): LiveData<List<ImageComment>> {
        val dao = myDatabase.getCommentsDao()
        return dao.getAllCommentsOfImage(imageId)
    }
}