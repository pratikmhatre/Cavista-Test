package com.example.cavista_test.screens.listing

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cavista_test.application.MyApplication
import com.example.cavista_test.data.network.responses.ImagesResponse
import com.example.cavista_test.databinding.ActivityImageListBinding
import com.example.cavista_test.di.components.DaggerActivityComponent
import com.example.cavista_test.di.modules.ActivityModule
import com.example.cavista_test.screens.details.ImageDetailsActivity
import com.example.cavista_test.utils.NetworkResponse
import com.example.cavista_test.utils.Result
import com.google.gson.Gson
import com.jakewharton.rxbinding.widget.RxTextView
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ImageListingActivity : AppCompatActivity(), ImageListAdapter.Callbacks {
    @Inject
    lateinit var imageListAdapter: ImageListAdapter

    @Inject
    lateinit var imageListingViewModel: ImageListingViewModel

    private lateinit var binding: ActivityImageListBinding
    private var imageList: List<ImagesResponse.Data.Image>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityComponent = DaggerActivityComponent.builder()
            .appComponent((applicationContext as MyApplication).getAppComponent()).activityModule(
                ActivityModule(this)
            ).build()
        activityComponent.inject(this)
        binding = ActivityImageListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        with(binding) {
            imageListActivity = this@ImageListingActivity
            swipeRefresh.isEnabled = false
            recyclerImages.apply {
                adapter = imageListAdapter
                layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            }

            //observe response live data
            imageListingViewModel.imageListLiveData.observe(this@ImageListingActivity, Observer {
                processResponse(it)
            })

            //adding debounce
            RxTextView.textChanges(edSearch)
                .debounce(250, TimeUnit.MILLISECONDS)
                .subscribe {
                    runOnUiThread {
                        if (it.trim().isNotBlank()) {
                            performSearch(it.toString())
                        }
                    }
                }
        }
    }

    private fun processResponse(response: NetworkResponse<List<ImagesResponse.Data.Image>>) {
        with(binding.swipeRefresh) {
            if (isRefreshing) {
                isRefreshing = false
            }
        }
        when (response.result) {
            Result.SUCCESS -> {
                if (!response.data.isNullOrEmpty()) {
                    imageList = response.data
                    imageListAdapter.addImages(imageList!!)
                } else {
                    imageListAdapter.clearList()
                    showToast("No results found for given query")
                }
            }
            Result.FAILURE -> {
                showToast("Unable to connect servers")
            }
            Result.SERVER_ERROR -> {
                showToast("Server Error")
            }
            Result.AUTH_FAILURE -> {
                showToast("Authorization error, please check header values")
            }
        }
    }

    private fun showToast(message: String, length: Int = Toast.LENGTH_LONG) {
        Toast.makeText(this, message, length).show()
    }

    //called from adapter
    override fun onImageClicked(image: ImagesResponse.Data.Image) {
        val intent = Intent(this@ImageListingActivity, ImageDetailsActivity::class.java)
        intent.putExtra("image", Gson().toJson(image))
        startActivity(intent)
    }


    private fun performSearch(query: String) {
        with(binding.swipeRefresh) {
            if (!isRefreshing) {
                isRefreshing = true
            }
        }
        imageListingViewModel.fetchResults(query)
    }

    fun onClearClicked(v: View) {
        binding.edSearch.setText("")
    }
}