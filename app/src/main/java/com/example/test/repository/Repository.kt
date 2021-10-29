package com.example.test.repository

import com.example.test.data.models.Data
import com.example.test.data.api.RetrofitInstance
import retrofit2.Response

class Repository {
    suspend fun getCustomPosts(term: String, media: String, limit: Int): Response<Data> {
        return RetrofitInstance.api.getDatas(term, media, limit)
    }
}