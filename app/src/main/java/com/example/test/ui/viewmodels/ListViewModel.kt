package com.example.test.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.test.data.models.Data
import com.example.test.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ListViewModel(private val repository: Repository) : ViewModel()  {

    var myResponse: MutableLiveData<Response<Data>> = MutableLiveData()
    val media: MutableLiveData<String> = MutableLiveData("movie")
    private val limit = 20

    fun getData(term: String) {
        viewModelScope.launch {
            val response = repository.getCustomPosts(term, media.value.toString(), limit)
            myResponse.value = response
        }
    }
}
class ListViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

