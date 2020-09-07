package com.example.cavista_test.listing

import androidx.lifecycle.MutableLiveData
import com.example.cavista_test.data.DataManager
import com.example.cavista_test.data.network.responses.ImagesResponse
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class ImageListingRepository @Inject constructor(val dataManager: DataManager) {
    val imageData = MutableLiveData<Response<ImagesResponse>>()
    fun fetchImageResults(query: String) {
        dataManager.getImageList(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<ImagesResponse>> {
                override fun onSuccess(t: Response<ImagesResponse>) {
                    imageData.postValue(t)
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

            })
    }
}