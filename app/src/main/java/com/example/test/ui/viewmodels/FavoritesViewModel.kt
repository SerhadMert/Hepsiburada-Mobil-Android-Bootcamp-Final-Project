package com.example.test.ui.viewmodels

import androidx.lifecycle.*
import com.example.test.ItunesApplication
import com.example.test.data.local.RoomDB
import com.example.test.data.models.Favorites
import com.example.test.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(private val repository: Repository) : ViewModel() {

    val readData = repository.readData()

    fun deleteFavorite(favorites: Favorites){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavorite(favorites)
        }
    }
}
class FavoritesViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoritesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}