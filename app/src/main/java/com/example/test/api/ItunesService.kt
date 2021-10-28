package com.example.test.api

import com.example.test.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesService {

    @GET("search?")
    suspend fun getDatas(
        @Query("term") term:String,
        @Query("media") media:String,
        @Query("limit") limit:Int
    ):Response<Data>
}