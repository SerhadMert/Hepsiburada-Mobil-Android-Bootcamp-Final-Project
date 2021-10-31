package com.example.test.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.test.data.models.Favorites
import com.example.test.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository) : ViewModel() {


    fun addFavorite(favorites: Favorites){
        viewModelScope.launch(Dispatchers.IO){
            repository.addFavorite(favorites)
        }
    }
    fun deleteFavorite(favorites: Favorites){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavorite(favorites)
        }
    }
    val readData = repository.readData()
}
class DetailViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}