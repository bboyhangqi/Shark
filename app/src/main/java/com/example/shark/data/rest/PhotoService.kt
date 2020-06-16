package com.example.shark.data.rest

import com.example.shark.data.model.PhotoList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {

    @GET("/services/rest?method=flickr.photos.search&format=json&nojsoncallback=1&extras=url_t,url_c,url_l,url_o")
    fun getPhotos(
        @Query("page") page: Int,
        @Query("text") type: String,
        @Query("api_key") key: String
    ): Single<PhotoList>

}