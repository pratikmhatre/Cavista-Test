package com.example.cavista_test.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.cavista_test.data.db.ImageComment

class CommentDiffCallbacks(
    val oldArrayList: ArrayList<ImageComment>,
    val newArrayList: List<ImageComment>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldArrayList.size

    override fun getNewListSize() = newArrayList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldArrayList[oldItemPosition].id == newArrayList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldArrayList[oldItemPosition].id == newArrayList[newItemPosition].id
}