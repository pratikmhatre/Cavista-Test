package com.example.cavista_test.di.modules

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cavista_test.screens.details.CommentsAdapter
import com.example.cavista_test.screens.details.ImageDetailsFactory
import com.example.cavista_test.screens.details.ImageDetailsViewModel
import com.example.cavista_test.screens.listing.ImageListAdapter
import com.example.cavista_test.screens.listing.ImageListingActivity
import com.example.cavista_test.screens.listing.ImageListingFactory
import com.example.cavista_test.screens.listing.ImageListingViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activityContext: AppCompatActivity) {
    @Provides
    fun provideImageListingViewModel(factory: ImageListingFactory): ImageListingViewModel {
        return ViewModelProvider(activityContext, factory)[ImageListingViewModel::class.java]
    }

    @Provides
    fun provideImageListAdapter() = ImageListAdapter(activityContext as ImageListingActivity)

    @Provides
    fun provideImageDetailsViewModel(factor: ImageDetailsFactory): ImageDetailsViewModel {
        return ViewModelProvider(activityContext, factor)[ImageDetailsViewModel::class.java]
    }

    @Provides
    fun provideCommentsAdapter(): CommentsAdapter = CommentsAdapter()
}