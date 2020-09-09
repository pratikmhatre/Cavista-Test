package com.example.cavista_test.screens.listing

import android.media.ImageReader
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cavista_test.R
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
        val callbacks = imageListingActivity as Callbacks
        fun initViews(imagePojo: ImagesResponse.Data.Image) {
            itemImageListBinding.ivImage.apply {
                Glide.with(imageListingActivity).load(imagePojo.link).apply(
                    RequestOptions().placeholder(
                        R.drawable.ic_image
                    )
                ).into(this)
                setOnClickListener {
                    callbacks.onImageClicked(imagePojo)
                }
            }
        }
    }

    fun addImages(arrayList: List<ImagesResponse.Data.Image>) {
        this.arrayList.clear()
        this.arrayList.addAll(arrayList)
        notifyDataSetChanged()
    }
    fun clearList() {
        this.arrayList.clear()
        notifyDataSetChanged()
    }
    interface Callbacks {
        fun onImageClicked(image: ImagesResponse.Data.Image)
    }
}