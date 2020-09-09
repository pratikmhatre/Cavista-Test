package com.example.cavista_test.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cavista_test.utils.AppConstants

@Dao
interface ImageCommentDao {
    @Insert
    fun addComment(comment: ImageComment)

    @Query("select * from ${AppConstants.COMMENTS_TABLE} where imageId = :imageId order by id desc")
    fun getAllCommentsOfImage(vararg imageId: String): LiveData<List<ImageComment>>

}