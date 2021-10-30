package com.example.test.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.test.ItunesApplication
import com.example.test.base.BaseFragment
import com.example.test.data.models.Favorites
import com.example.test.databinding.FragmentFavoritesBinding
import com.example.test.ui.adapters.FavoritesAdapter
import com.example.test.ui.viewmodels.FavoritesViewModel
import com.example.test.ui.viewmodels.FavoritesViewModelFactory


class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>(FragmentFavoritesBinding::inflate,true){

    private val viewModel : FavoritesViewModel by viewModels {
        FavoritesViewModelFactory((context?.applicationContext as ItunesApplication).repository)
    }
    private val adapter by lazy { FavoritesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFavorites()
        initRV()

    }

    private fun initFavorites() {
        adapter.setData(viewModel.readData)
    }
    private fun initRV() {
        binding.recyclerFavorites.adapter = adapter
    }
}
