package com.example.test.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.data.models.DataResult
import com.example.test.data.models.Favorites
import com.example.test.databinding.ItemFavoritesBinding
import com.example.test.ui.fragments.ListFragmentDirections

class FavoritesAdapter:RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {
    private var list = emptyList<Favorites>()
    class FavoritesViewHolder(private val binding: ItemFavoritesBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(favorite : Favorites){
                binding.apply {
                    Glide.with(imageView.context).load(favorite.artworkUrl100).into(imageView)
                    when (favorite.kind) {
                        "feature-movie", "music" -> {
                            textCollectionPrice.text = favorite.trackPrice.toString()
                        }
                        "software", "ebook" -> {
                            textCollectionPrice.text = favorite.price.toString()
                        }
                    }
                    textCollectionName.text = favorite.trackName
                    textCurrency.text = favorite.currency
                    textReleaseDate.text = favorite.releaseDate?.substring(0,10)
                    crdItem.setOnClickListener {
                    }
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val view = LayoutInflater.from(parent.context)
        return FavoritesViewHolder(ItemFavoritesBinding.inflate(view,parent,false))
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setData(newList: List<Favorites>){
        list= ArrayList(newList)
        notifyDataSetChanged()
    }
}
