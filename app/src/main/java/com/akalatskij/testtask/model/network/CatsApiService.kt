package com.akalatskij.testtask.model.network

import com.akalatskij.testtask.model.entity.CatJson
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CatsApiService {

    @Headers
    ("x-api-key: 57a32f2c-1a74-4c8d-aa27-4e7110f27ee1")
    @GET("images/search")
    fun getCats(@Query("limit") limit: Int):
            Observable<List<CatJson>>

    companion object {
        fun create(): CatsApiService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://api.thecatapi.com/v1/")
                .build()

            return retrofit.create(CatsApiService::class.java)
        }
    }
}