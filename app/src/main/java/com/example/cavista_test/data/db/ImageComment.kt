package com.example.cavista_test.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cavista_test.utils.AppConstants

@Entity(tableName = AppConstants.COMMENTS_TABLE)
class ImageComment {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var imageId: String? = null
    var comment: String? = null
    var dateCreated: String? = null
}