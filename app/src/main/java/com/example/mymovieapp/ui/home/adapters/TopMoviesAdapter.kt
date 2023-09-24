package com.example.mymovieapp.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mymovieapp.databinding.ItemHomeGenreListBinding
import com.example.mymovieapp.databinding.ItemHomeMoviesTopBinding
import com.example.mymovieapp.models.home.ResponseTopMoviesList
import javax.inject.Inject

class TopMoviesAdapter @Inject constructor() :
    RecyclerView.Adapter<TopMoviesAdapter.TopMoviesViewHolder>() {

    lateinit var binding: ItemHomeMoviesTopBinding

    inner class TopMoviesViewHolder(item: View) : RecyclerView.ViewHolder(item) {


        fun onBind(oneOfItems: ResponseTopMoviesList.Data) {

            binding.movieNameTxt.text = oneOfItems.title
            binding.movieInfoTxt.text = "${oneOfItems.country} ${oneOfItems.id} ${oneOfItems.year}"
            binding.moviePosterImg.load(oneOfItems.poster) {
                crossfade(true)
                crossfade(800)
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMoviesViewHolder {
        binding =
            ItemHomeMoviesTopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopMoviesViewHolder(binding.root)


    }

    override fun onBindViewHolder(holder: TopMoviesViewHolder, position: Int) {
        holder.onBind(differ.currentList[position])
        holder.setIsRecyclable(false)

    }

    override fun getItemCount() = if (differ.currentList.size > 5) 5 else differ.currentList.size


    val differCallBack = object : DiffUtil.ItemCallback<ResponseTopMoviesList.Data>() {
        override fun areItemsTheSame(
            oldItem: ResponseTopMoviesList.Data,
            newItem: ResponseTopMoviesList.Data
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseTopMoviesList.Data,
            newItem: ResponseTopMoviesList.Data
        ): Boolean {
            return oldItem == newItem

        }
    }


    val differ = AsyncListDiffer(this, differCallBack)
}