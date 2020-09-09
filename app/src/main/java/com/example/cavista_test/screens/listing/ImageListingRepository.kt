package com.example.cavista_test.screens.listing

import androidx.lifecycle.MutableLiveData
import com.example.cavista_test.data.DataManager
import com.example.cavista_test.data.network.responses.ImagesResponse
import com.example.cavista_test.utils.NetworkResponse
import com.example.cavista_test.utils.Result
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class ImageListingRepository @Inject constructor(val dataManager: DataManager) {
    val compositeDisposable = CompositeDisposable()
    val imageData = MutableLiveData<NetworkResponse<Response<ImagesResponse>>>()

    fun fetchImageResults(query: String) {
        //clear previous calls
        compositeDisposable.clear()

        //call search service
        dataManager.getImageList(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<ImagesResponse>> {
                override fun onSuccess(t: Response<ImagesResponse>) {
                    imageData.postValue(NetworkResponse(result = Result.SUCCESS, data = t))
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }
                override fun onError(e: Throwable) {
                    imageData.postValue(NetworkResponse(result = Result.FAILURE, data = null))
                    e.printStackTrace()
                }
            })
    }
}