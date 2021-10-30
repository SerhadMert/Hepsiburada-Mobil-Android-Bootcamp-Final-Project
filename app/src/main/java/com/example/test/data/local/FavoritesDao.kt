package com.example.test.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.test.data.models.Favorites
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM favorites ORDER BY id DESC")
    fun getFavorites(): Flow<List<Favorites>>

    @Insert
    suspend fun addFavorites(favorites: Favorites)

    @Delete
    suspend fun deleteFavorites(favorites: Favorites)

    @Query("DELETE FROM favorites")
    suspend fun deleteAll()
}