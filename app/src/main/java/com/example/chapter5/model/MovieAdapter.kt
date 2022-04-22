package com.example.chapter5.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chapter5.databases.ResultX
import com.example.chapter5.databinding.ItemGridBinding

class MovieAdapter (private val onClick:(ResultX)->Unit)
    : ListAdapter<ResultX, MovieAdapter.ViewHolder>(ResultXComparator()) {


    class ViewHolder(private val binding: ItemGridBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(currentResultX: ResultX,
                 onClick: (ResultX) -> Unit){

            binding.apply {
                judul.text = currentResultX.title
                Glide.with(binding.imageView)
                    .load("https://image.tmdb.org/t/p/w500"+currentResultX.posterPath)
                    .into(imageView)
                root.setOnClickListener {
                    onClick(currentResultX)
                }
            }

        }

    }

    class ResultXComparator : DiffUtil.ItemCallback<ResultX>() {
        override fun areItemsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGridBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

}