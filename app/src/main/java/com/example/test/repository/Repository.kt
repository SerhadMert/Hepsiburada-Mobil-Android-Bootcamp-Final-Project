package com.example.test.repository

import com.example.test.data.local.FavoritesDao
import com.example.test.data.models.Data
import com.example.test.data.models.Favorites
import com.example.test.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class Repository(private val favoritesDao: FavoritesDao) {

    suspend fun getCustomPosts(term: String, media: String, limit: Int): Response<Data> {
        return RetrofitInstance.api.getDatas(term, media, limit)
    }

    fun readData(): List<Favorites> = favoritesDao.getFavorites()

    suspend fun addFavorite(favorites: Favorites) = favoritesDao.addFavorites(favorites)

    suspend fun deleteFavorite(favorites: Favorites) = favoritesDao.deleteFavorites(favorites)

}