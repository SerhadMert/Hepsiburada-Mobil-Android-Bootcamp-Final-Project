package com.example.test.ui.fragments

import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import androidx.fragment.app.viewModels
import com.example.test.ItunesApplication
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentFavoritesBinding
import com.example.test.ui.adapters.FavoritesAdapter
import com.example.test.ui.viewmodels.FavoritesViewModel
import com.example.test.ui.viewmodels.FavoritesViewModelFactory


class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>(FragmentFavoritesBinding::inflate,true){

    private val viewModel: FavoritesViewModel by viewModels {
        FavoritesViewModelFactory((context?.applicationContext as ItunesApplication).repository)
    }
    private val adapter by lazy { FavoritesAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
    }

    private fun initRV() {
        if (!viewModel.getFavorites().isNullOrEmpty()) {
            adapter.setData(viewModel.getFavorites())
            binding.recyclerFavorites.adapter = adapter
        } else {
            binding.txtNoFavorite.visibility = VISIBLE
        }
    }
}
