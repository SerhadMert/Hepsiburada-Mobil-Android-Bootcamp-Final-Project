package com.example.test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ListViewModel:ViewModel() {

    private val repository=Repository()
    var myResponse: MutableLiveData<Response<Data>> = MutableLiveData()
    val media: MutableLiveData<String> = MutableLiveData("")
    private val limit=20

    fun getData(term:String){
        viewModelScope.launch {
            val response = repository.getCustomPosts(term,media.value.toString(),limit)
            myResponse.value=response
        }
    }
}