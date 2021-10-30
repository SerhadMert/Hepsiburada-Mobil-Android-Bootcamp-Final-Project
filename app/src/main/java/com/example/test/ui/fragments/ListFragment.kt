package com.example.test.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.test.ItunesApplication
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentListBinding
import com.example.test.ui.adapters.MyAdapter
import com.example.test.ui.viewmodels.ListViewModel
import com.example.test.ui.viewmodels.ListViewModelFactory

class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate,true){

    private val myAdapter by lazy { MyAdapter() }
    private val viewModel : ListViewModel by viewModels {
        ListViewModelFactory((context?.applicationContext as ItunesApplication).repository)
    }
    private var text =""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        search()
        initChips()
        onScrollListener()
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
                        myAdapter.setData(emptyList())
                        binding.recyclerHome.scrollToPosition(0)
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
                viewModel.media.value = "movie"
                viewModel.limit.value = 20
                myAdapter.setData(emptyList())
                binding.recyclerHome.scrollToPosition(0)
                viewModel.getData(text)
                getDatas()
            }
            chipMusics.setOnClickListener {
                viewModel.media.value = "music"
                viewModel.limit.value = 20
                myAdapter.setData(emptyList())
                recyclerHome.scrollToPosition(0)
                viewModel.getData(text)
                getDatas()
            }
            chipBooks.setOnClickListener {
                viewModel.media.value="ebook"
                viewModel.limit.value = 20
                myAdapter.setData(emptyList())
                binding.recyclerHome.scrollToPosition(0)
                viewModel.getData(text)
                getDatas()
            }
            chipApps.setOnClickListener {
                viewModel.media.value = "software"
                viewModel.limit.value = 20
                myAdapter.setData(emptyList())
                binding.recyclerHome.scrollToPosition(0)
                viewModel.getData(text)
                getDatas()
            }
        }
    }

    private fun initRV(){
        binding.recyclerHome.adapter=myAdapter
    }

    private fun onScrollListener() {
        binding.recyclerHome.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.recyclerHome.canScrollVertically(1) &&
                    newState == RecyclerView.SCROLL_STATE_IDLE
                ) {
                    if (viewModel.limit.value!! < 200) {
                        viewModel.limitChanger()
                        Log.d("LogLimit",viewModel.limit.value.toString())
                    }
                    viewModel.getData(text)
                    getDatas()
                }
            }
        })
    }
}
