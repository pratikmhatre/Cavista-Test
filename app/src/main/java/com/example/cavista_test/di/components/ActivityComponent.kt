package com.example.cavista_test.di.components

import com.example.cavista_test.di.annotations.PerActivity
import com.example.cavista_test.di.modules.ActivityModule
import com.example.cavista_test.listing.ImageListingActivity
import dagger.Component

@PerActivity
@Component(modules = [ActivityModule::class], dependencies = [AppComponent::class])
interface ActivityComponent {
    fun inject(listingActivity: ImageListingActivity)
}