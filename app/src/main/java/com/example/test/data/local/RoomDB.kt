package com.example.test.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.test.ItunesApplication
import com.example.test.data.models.Favorites
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Favorites::class],version = 1)
abstract class RoomDB: RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
    companion object{
        @Volatile
        var INSTANCE: RoomDB? = null
        fun getDatabase(context: ItunesApplication,scope: CoroutineScope): RoomDB{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "itunes_database"
                )
                    .addCallback(FavoriteDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
private class FavoriteDatabaseCallback(
    private val scope: CoroutineScope
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        RoomDB.INSTANCE?.let { database ->
            scope.launch {
                populateDatabase(database.favoritesDao())
            }
        }
    }

    suspend fun populateDatabase(favoritesDao: FavoritesDao) {
        // Delete all content here.
        favoritesDao.deleteAll()
    }
}
