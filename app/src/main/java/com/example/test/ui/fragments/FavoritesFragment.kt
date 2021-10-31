package com.example.test.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.test.ItunesApplication
import com.example.test.base.BaseFragment
import com.example.test.data.models.Favorites
import com.example.test.databinding.FragmentFavoritesBinding
import com.example.test.ui.adapters.FavoritesAdapter
import com.example.test.ui.viewmodels.FavoritesViewModel
import com.example.test.ui.viewmodels.FavoritesViewModelFactory


class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>(FragmentFavoritesBinding::inflate,true){

    private val viewModel: FavoritesViewModel by viewModels {
        FavoritesViewModelFactory((context?.applicationContext as ItunesApplication).repository)
    }
    private val adapter by lazy { FavoritesAdapter() }
    private val favorites by lazy { viewModel.readData as ArrayList<Favorites> }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
    }

    private fun initRV() {
        adapter.setData(favorites)
        binding.recyclerFavorites.adapter = adapter
    }
}
