package com.example.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.databinding.ItemDataBinding

class MyAdapter :RecyclerView.Adapter<MyAdapter.HomeClassHolder>(){

    private var list= emptyList<DataResult>()

    class HomeClassHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(datas: DataResult){
            Glide.with(binding.imageView.context)
                .load(datas.artworkUrl100).into(binding.imageView)
            binding.textCollectionName.text = datas.collectionName
            binding.textCollectionPrice.text = datas.collectionPrice.toString()
            binding.textReleaseDate.text = datas.releaseDate
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