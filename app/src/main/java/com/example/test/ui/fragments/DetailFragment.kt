package com.example.test.ui.fragments

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.test.ItunesApplication
import com.example.test.R
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
    private val img by lazy { args.currentItem.artworkUrl100?.replace("100x100bb.jpg","200x200bb.jpg") }
    private val favorite by lazy { Favorites(
        args.currentItem.trackId,
        img,
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
                imgLikeAdd.setImageResource(R.drawable.ic_heart_filled)
            } else {
                imgLikeAdd.setImageResource(R.drawable.ic_heart_empty)
            }
        }
    }

    private fun initBinding() {
        binding.apply {
            Glide.with(imgArt100.context).load(img).into(imgArt100)
            txtName.text = args.currentItem.trackName
            txtCurrency.text = args.currentItem.currency
            when (args.currentItem.kind) {
                "feature-movie" -> {
                    txtPrice.text = args.currentItem.trackPrice.toString()
                    if(args.currentItem.longDescription!=null){
                    txtDescription.text = Html.fromHtml(args.currentItem.longDescription)
                    } else{
                        txtDescription.text = Html.fromHtml(args.currentItem.shortDescription)
                    }
                }
                "software", "ebook" -> {
                    txtPrice.text = args.currentItem.price.toString()
                    txtDescription.text = Html.fromHtml(args.currentItem.description)
                }
                "music" ->{
                    txtPrice.text = args.currentItem.trackPrice.toString()
                    txtDescription.text = getString(R.string.no_description)
                }
            }
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
        updateFavoriteButtons()
    }

    private fun initFavoriteClickListener() {
        binding.apply {
            imgLikeAdd.setOnClickListener {
                if (!isInFavorite()) {
                    viewModel.addFavorite(favorite)
                    imgLikeAdd.setImageResource(R.drawable.ic_heart_filled)
                    Toast.makeText(
                        context, "Successfully added to favorites", Toast.LENGTH_LONG
                    ).show()
                } else {
                    viewModel.deleteFavorite(favorite)
                    imgLikeAdd.setImageResource(R.drawable.ic_heart_empty)
                    Toast.makeText(
                        context, "Successfully deleted from favorites", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}
