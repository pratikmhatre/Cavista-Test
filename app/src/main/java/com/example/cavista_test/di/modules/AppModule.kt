package com.example.cavista_test.di.modules

import android.content.Context
import androidx.room.Room
import com.example.cavista_test.data.DataManager
import com.example.cavista_test.data.DataManagerClass
import com.example.cavista_test.data.db.MyDatabase
import com.example.cavista_test.data.network.ApiList
import com.example.cavista_test.utils.AppConstants
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(val appContext: Context) {
    @Provides
    @Singleton
    fun provideApiList(): ApiList {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        val headersInterceptor = Interceptor { chain ->
            val request = chain.request()
            val builder =
                request.newBuilder().addHeader("Authorization", "Client-ID 137cda6b5008a7c")
            return@Interceptor chain.proceed(builder.build())
        }



        val httpClient = OkHttpClient().newBuilder().addInterceptor(headersInterceptor).addInterceptor(loggingInterceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.imgur.com/")
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiList::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(): MyDatabase {
        return Room.databaseBuilder(appContext, MyDatabase::class.java, AppConstants.COMMENTS_TABLE)
            .build()
    }

    @Singleton
    @Provides
    fun provideDataManager(dataManagerClass: DataManagerClass): DataManager {
        return dataManagerClass
    }
}