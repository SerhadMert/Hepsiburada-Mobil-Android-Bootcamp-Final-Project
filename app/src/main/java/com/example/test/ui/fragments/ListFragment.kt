package com.example.test.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.test.ui.viewmodels.ListViewModel
import com.example.test.ui.MyAdapter
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentListBinding

class ListFragment:BaseFragment<FragmentListBinding>(FragmentListBinding::inflate){

    private val myAdapter by lazy { MyAdapter() }
    private val viewModel by viewModels<ListViewModel>()
    private var text =""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        search()
        initChips()
    }

    private fun getDatas(){
        viewModel.myResponse.observe(viewLifecycleOwner){response->
            if(response.isSuccessful){
                response.body()?.let { myAdapter.setData(it.results) }
            }else{
                Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun search() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                when(newText!!.length > 2) {
                    true -> {
                        text=newText
                        viewModel.getData(text)
                        getDatas()
                    }
                }
                return true
            }
        })
    }

    private fun initChips(){
        binding.apply {

            chipMovies.setOnClickListener {
                viewModel.media.value="movie"
                viewModel.getData(text)
                getDatas()
            }
            chipMusics.setOnClickListener {
                viewModel.media.value="music"
                viewModel.getData(text)
                getDatas()
            }
            chipBooks.setOnClickListener {
                viewModel.media.value="ebook"
                viewModel.getData(text)
                getDatas()
            }
            chipApps.setOnClickListener {
                viewModel.media.value="software"
                viewModel.getData(text)
                getDatas()
            }
        }
    }

    private fun initRV(){
        binding.recyclerHome.adapter=myAdapter
    }
}
