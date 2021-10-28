package com.example.test.repository

import com.example.test.Data
import com.example.test.DataResult
import com.example.test.api.RetrofitInstance
import retrofit2.Response

class Repository {
    suspend fun getCustomPosts(term: String, media: String, limit: Int): Response<Data> {
        return RetrofitInstance.api.getDatas(term, media, limit)
    }
}