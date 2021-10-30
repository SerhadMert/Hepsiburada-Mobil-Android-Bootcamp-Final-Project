package com.example.test.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.data.models.DataResult
import com.example.test.databinding.ItemDataBinding
import com.example.test.ui.fragments.ListFragmentDirections

class MyAdapter :RecyclerView.Adapter<MyAdapter.HomeClassHolder>(){

    private var list= emptyList<DataResult>()

    class HomeClassHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(datas: DataResult){
            binding.apply {
                Glide.with(imageView.context).load(datas.artworkUrl100).into(imageView)
                when (datas.kind) {
                    "feature-movie", "music" -> {
                        textCollectionPrice.text = datas.trackPrice.toString()
                    }
                    "software", "ebook" -> {
                        textCollectionPrice.text = datas.price.toString()
                    }
                }
                textCollectionName.text = datas.trackName
                textCurrency.text = datas.currency
                textReleaseDate.text = datas.releaseDate?.substring(0,10)
                crdItem.setOnClickListener {
                    val action = ListFragmentDirections.actionListFragmentToDetailFragment(datas)
                    it.findNavController().navigate(action)
                }
            }
        }
        /*companion object{
            val diffCallback = object : DiffUtil.ItemCallback<Result>(){
                override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                    return oldItem.trackId == newItem.trackId
                }

                override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                    return oldItem == newItem
                }
            }
        }*/

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeClassHolder {
        val view = LayoutInflater.from(parent.context)
        return HomeClassHolder(ItemDataBinding.inflate(view,parent,false))
    }

    override fun onBindViewHolder(holder: HomeClassHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int =list.size

    fun setData(newList: List<DataResult>){
        list= ArrayList(newList)
        notifyDataSetChanged()
    }
}