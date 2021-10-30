package com.example.test.ui.fragments

import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.test.ItunesApplication
import com.example.test.base.BaseFragment
import com.example.test.data.models.Favorites
import com.example.test.databinding.FragmentDetailBinding
import com.example.test.ui.viewmodels.DetailViewModel
import com.example.test.ui.viewmodels.DetailViewModelFactory


class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate,false) {

    private val args : DetailFragmentArgs by navArgs()
    private val viewModel : DetailViewModel by viewModels {
        DetailViewModelFactory((context?.applicationContext as ItunesApplication).repository)
    }
    private val favorite by lazy { Favorites(
        args.currentItem.trackId,
        args.currentItem.artworkUrl100,
        args.currentItem.trackName,
        args.currentItem.kind,
        args.currentItem.trackPrice,
        args.currentItem.price,
        args.currentItem.currency,
        args.currentItem.releaseDate,
        args.currentItem.description,
        args.currentItem.longDescription,
        args.currentItem.shortDescription
    ) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding()
        initFavoriteClickListener()
    }

    private fun isInFavorite(): Boolean {
        var isInFavorite = false

            for( data in viewModel.readData){
            isInFavorite = data.trackId == args.currentItem.trackId
            if(isInFavorite) break
            }
        return isInFavorite
    }
    private fun updateFavoriteButtons() {
        binding.apply {
            if (isInFavorite()) {
                imgLikeAdd.visibility = INVISIBLE
                imgLikeDelete.visibility = VISIBLE
            } else {
                imgLikeDelete.visibility = INVISIBLE
                imgLikeAdd.visibility = VISIBLE
            }
        }

    }

    private fun initBinding(){
        binding.apply {
            Glide.with(imgArt100.context).load(args.currentItem.artworkUrl100).into(imgArt100)
            txtName.text = args.currentItem.trackName
            txtCurrency.text = args.currentItem.currency
            when(args.currentItem.kind){
                "feature-movie","music" -> txtPrice.text = args.currentItem.trackPrice.toString()
                "software","ebook" -> txtPrice.text = args.currentItem.price.toString()
            }
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
        updateFavoriteButtons()
    }

    private fun initFavoriteClickListener(){
        binding.apply {
            imgLikeAdd.setOnClickListener {
                viewModel.addFavorite(favorite)
                imgLikeAdd.visibility = INVISIBLE
                imgLikeDelete.visibility = VISIBLE
                Toast.makeText(
                    context, "Successfully added to favorites", Toast.LENGTH_LONG
                ).show()
            }
            imgLikeDelete.setOnClickListener {
                viewModel.deleteFavorite(favorite)
                imgLikeDelete.visibility = INVISIBLE
                imgLikeAdd.visibility = VISIBLE
                Toast.makeText(
                    context, "Successfully deleted from favorites", Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
