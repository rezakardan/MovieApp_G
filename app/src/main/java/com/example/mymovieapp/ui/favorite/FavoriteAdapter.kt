package com.example.mymovieapp.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mymovieapp.databinding.ItemHomeMoviesLastBinding
import com.example.mymovieapp.db.MovieEntity
import javax.inject.Inject

class FavoriteAdapter @Inject constructor() :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    lateinit var binding: ItemHomeMoviesLastBinding

    private var movieList = emptyList<MovieEntity>()

    inner class FavoriteViewHolder(item: View) : RecyclerView.ViewHolder(item) {


        fun onBind(item: MovieEntity) {

            binding.movieNameTxt.text = item.name.toString()
            binding.movieYearTxt.text = item.year.toString()
            binding.movieRateTxt.text = item.rate.toString()
            binding.movieCountryTxt.text = item.country.toString()
            binding.moviePosterImg.load(item.poster.toString())




            binding.root.setOnClickListener {

               onItemClickListener?.let {

                   it(item)


               }


            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {

        binding =
            ItemHomeMoviesLastBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FavoriteViewHolder(binding.root)

    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.onBind(movieList[position])
    }

    override fun getItemCount() = movieList.size




    private var onItemClickListener:((MovieEntity)->Unit)?=null


    fun setOnItemClickListener(listener:(MovieEntity)->Unit){



        onItemClickListener=listener


    }






    fun setData(data: List<MovieEntity>) {

        val movieDiffutil = MovieDiffUtils(movieList, data)

        val diffutil = DiffUtil.calculateDiff(movieDiffutil)

        movieList = data

        diffutil.dispatchUpdatesTo(this)


    }


    class MovieDiffUtils(
        private val oldItem: List<MovieEntity>,
        private val newItem: List<MovieEntity>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]

        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }


    }
}