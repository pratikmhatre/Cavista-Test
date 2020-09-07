package com.example.cavista_test.listing

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cavista_test.application.MyApplication
import com.example.cavista_test.databinding.ActivityImageListBinding
import com.example.cavista_test.di.components.DaggerActivityComponent
import com.example.cavista_test.di.modules.ActivityModule
import kotlinx.android.synthetic.main.activity_image_list.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ImageListingActivity : AppCompatActivity() {
    @Inject
    lateinit var imageListAdapter: ImageListAdapter

    @Inject
    lateinit var imageListingViewModel: ImageListingViewModel

    private lateinit var binding: ActivityImageListBinding
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
            setSupportActionBar(toolbar)
            recyclerImages.apply {
                adapter = imageListAdapter
                layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            }
            swipeRefresh.isEnabled = false
        }

        imageListingViewModel.imageListLiveData.observe(this@ImageListingActivity, Observer {
            if (binding.swipeRefresh.isRefreshing) {
                binding.swipeRefresh.isRefreshing = false
            }
            imageListAdapter.addImages(it)
        })
    }

    private fun performSearch() {
        if (!binding.swipeRefresh.isRefreshing) {
            binding.swipeRefresh.isRefreshing = true
        }
        imageListingViewModel.fetchResults(ed_search.text.toString())
    }

    fun onClearClicked(v: View) {
//        binding.edSearch.setText("")
        performSearch()
    }
}