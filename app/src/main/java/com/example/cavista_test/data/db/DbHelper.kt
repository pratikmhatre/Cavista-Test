package com.example.cavista_test.data.db

import androidx.lifecycle.LiveData

interface DbHelper {
    fun insertComment(imageComment: ImageComment)
    fun getCommentsOfImage(imageId: String): LiveData<List<ImageComment>>
}