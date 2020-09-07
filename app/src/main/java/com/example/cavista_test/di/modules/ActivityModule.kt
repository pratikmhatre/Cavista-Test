package com.example.cavista_test.di.modules

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cavista_test.di.annotations.PerActivity
import com.example.cavista_test.listing.*
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
}