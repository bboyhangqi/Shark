package com.example.shark.ui.list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.shark.data.model.Photo
import com.example.shark.data.model.PhotoList
import com.example.shark.data.rest.PhotoRepository
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class ListViewModel : ViewModel() {

    val photos: MutableLiveData<List<Photo>> = MutableLiveData()

    private val mDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    fun loadPhotos(type: String, page: Int) {
        mDisposable.add(
            PhotoRepository.getPhotoList(page, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PhotoList>() {
                    override fun onSuccess(photoList: PhotoList?) {
                        val gson = Gson()
                        Log.e(Companion.TAG, "zhq.debug here"+ gson.toJson(photoList))
                        photos.value = photoList!!.photos.photo
                    }

                    override fun onError(e: Throwable?) {
                        Log.e(Companion.TAG, "zhq.debug "+e.toString())
                    }
                })
        )
    }

    companion object {
        private const val TAG = "ListViewModel"
    }

}