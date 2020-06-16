package com.example.shark.data.rest

import com.example.shark.data.model.PhotoList
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object PhotoRepository {

    private const val API_KEY = "949e98778755d1982f537d56236bbb42"

    private val service: PhotoService by lazy {
        Retrofit.Builder().baseUrl("https://api.flickr.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PhotoService::class.java)
    }

    fun getPhotoList(page: Int, type: String): Single<PhotoList> {
        return service.getPhotos(page, type, API_KEY)
    }

}