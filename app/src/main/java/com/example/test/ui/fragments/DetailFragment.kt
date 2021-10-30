package com.example.test.ui.fragments

import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
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
    private var isInFavorite = false
    private val viewModel : DetailViewModel by viewModels {
        DetailViewModelFactory((context?.applicationContext as ItunesApplication).repository)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding()
        initClickListener()
    }

    private fun initBinding(){
        binding.apply {
            Glide.with(imgArt100.context).load(args.currentItem.artworkUrl100).into(imgArt100)
            txtName.text = args.currentItem.trackName
            txtKind.text = args.currentItem.kind
            txtDate.text = args.currentItem.releaseDate!!.substring(0,10)
            when(args.currentItem.kind){
                "feature-movie","music" -> txtPrice.text = args.currentItem.trackPrice.toString()
                "software","ebook" -> txtPrice.text = args.currentItem.price.toString()
            }
        }
    }

    private fun initClickListener(){
        binding.apply {
            imgLikeAdd.setOnClickListener {
                args.currentItem.let {
                    if(!isInFavorite){
                        viewModel.addFavorite(
                            Favorites(
                                0,
                                args.currentItem.artworkUrl100,
                                args.currentItem.trackName,
                                args.currentItem.kind,
                                args.currentItem.trackPrice,
                                args.currentItem.price,
                                args.currentItem.currency,
                                args.currentItem.releaseDate,
                                args.currentItem.description,
                                args.currentItem.longDescription,
                                args.currentItem.shortDescription,
                            )
                        )
                        isInFavorite = true
                    }
                }
                imgLikeAdd.visibility = INVISIBLE
                imgLikeDelete.visibility = VISIBLE
            }
            imgLikeDelete.setOnClickListener {
                args.currentItem.let {
                    if (isInFavorite) {
                        viewModel.deleteFavorite(
                            Favorites(
                                0,
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
                            )
                        )
                        isInFavorite = false
                    }
                }
                imgLikeDelete.visibility = INVISIBLE
                imgLikeAdd.visibility = VISIBLE
            }
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}