package com.example.cavista_test.di.components

import com.example.cavista_test.data.DataManager
import com.example.cavista_test.data.network.ApiList
import com.example.cavista_test.di.modules.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun provideDataManager(): DataManager
}