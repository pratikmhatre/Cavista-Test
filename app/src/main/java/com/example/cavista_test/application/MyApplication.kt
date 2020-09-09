package com.example.cavista_test.application

import android.app.Application
import com.example.cavista_test.di.components.AppComponent
import com.example.cavista_test.di.components.DaggerAppComponent
import com.example.cavista_test.di.modules.AppModule

class MyApplication : Application() {
    private lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    fun getAppComponent() = appComponent
}