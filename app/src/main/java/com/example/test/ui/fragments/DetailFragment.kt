package com.example.test.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentDetailBinding


class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val args : DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding()
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

}