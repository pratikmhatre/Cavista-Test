package com.example.cavista_test.listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cavista_test.data.network.responses.ImagesResponse
import com.example.cavista_test.databinding.ItemImageListBinding

class ImageListAdapter(val imageListingActivity: ImageListingActivity) :
    RecyclerView.Adapter<ImageListAdapter.ImageHolder>() {

    private val arrayList = ArrayList<ImagesResponse.Data.Image>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val rootBinding =
            ItemImageListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageHolder(rootBinding)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.initViews(arrayList[position])
    }

    override fun getItemCount() = arrayList.size

    inner class ImageHolder(private val itemImageListBinding: ItemImageListBinding) :
        RecyclerView.ViewHolder(itemImageListBinding.root) {
        fun initViews(imagePojo: ImagesResponse.Data.Image) {
            Glide.with(imageListingActivity).load(imagePojo.link).into(itemImageListBinding.ivImage)
        }
    }

    fun addImages(arrayList: ArrayList<ImagesResponse.Data.Image>) {
        this.arrayList.clear()
        this.arrayList.addAll(arrayList)
        notifyDataSetChanged()
    }
}