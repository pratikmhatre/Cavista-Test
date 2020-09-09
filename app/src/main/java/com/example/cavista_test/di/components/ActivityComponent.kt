package com.example.cavista_test.di.components

import com.example.cavista_test.di.annotations.PerActivity
import com.example.cavista_test.di.modules.ActivityModule
import com.example.cavista_test.screens.details.ImageDetailsActivity
import com.example.cavista_test.screens.listing.ImageListingActivity
import dagger.Component

@PerActivity
@Component(modules = [ActivityModule::class], dependencies = [AppComponent::class])
interface ActivityComponent {
    fun inject(listingActivity: ImageListingActivity)
    fun inject(detailsActivity: ImageDetailsActivity)
}