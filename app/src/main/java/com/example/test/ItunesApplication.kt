package com.example.test

import android.app.Application
import com.example.test.data.local.RoomDB
import com.example.test.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ItunesApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())
    // Using by lazy so the database is only created when it's needed
    // rather than when the application starts
    val database by lazy { RoomDB.getDatabase(this,applicationScope) }
    val repository by lazy { Repository(database.favoritesDao()) }
}