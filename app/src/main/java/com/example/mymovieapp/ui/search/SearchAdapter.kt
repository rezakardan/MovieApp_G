package com.example.mymovieapp.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mymovieapp.databinding.ItemHomeMoviesLastBinding
import com.example.mymovieapp.db.MovieEntity
import com.example.mymovieapp.models.home.ResponseTopMoviesList
import javax.inject.Inject

class SearchAdapter @Inject constructor() : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    lateinit var binding: ItemHomeMoviesLastBinding


    private var moviesList = emptyList<ResponseTopMoviesList.Data>()

    inner class SearchViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun onBind(item: ResponseTopMoviesList.Data) {


            binding.movieCountryTxt.text=item.country.toString()

            binding.movieRateTxt.text=item.imdbRating.toString()
            binding.movieYearTxt.text=item.year
            binding.movieNameTxt.text=item.title.toString()

            binding.moviePosterImg.load(item.poster.toString())




            binding.root.setOnClickListener {


                onItemClickListener?.let {


                    it(item)


                }


            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {


        binding =
            ItemHomeMoviesLastBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SearchViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {

        holder.onBind(moviesList[position])
        holder.setIsRecyclable(false)

    }

    override fun getItemCount()=moviesList.size




    private var onItemClickListener:((ResponseTopMoviesList.Data)->Unit)?=null

    fun setOnItemClickListener(listener:(ResponseTopMoviesList.Data)->Unit){

      onItemClickListener=listener





    }







    fun setData(data:List<ResponseTopMoviesList.Data>){

        val moviesDiffUtill=MoviesDiffUtils(data,moviesList)


     val diffUtill=   DiffUtil.calculateDiff(moviesDiffUtill)

        moviesList=data

        diffUtill.dispatchUpdatesTo(this)




    }




    class MoviesDiffUtils(
        private val newItem: List<ResponseTopMoviesList.Data>,
        private val oldItem: List<ResponseTopMoviesList.Data>
    ):DiffUtil.Callback(){
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
return oldItem[oldItemPosition]===newItem[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition]===newItem[newItemPosition]
        }


    }


}