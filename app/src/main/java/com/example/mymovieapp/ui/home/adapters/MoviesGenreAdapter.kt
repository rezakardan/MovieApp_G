package com.example.mymovieapp.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieapp.databinding.ItemHomeGenreListBinding
import com.example.mymovieapp.models.home.ResponseGnerMovies
import javax.inject.Inject

class MoviesGenreAdapter @Inject constructor():RecyclerView.Adapter<MoviesGenreAdapter.GenreViewHolder>() {



    lateinit var binding:ItemHomeGenreListBinding

    inner class GenreViewHolder(item:View):RecyclerView.ViewHolder(item){


        fun onBind(oneOfItems:ResponseGnerMovies.ResponseGnerMoviesItem){


            binding.nameTxt.text=oneOfItems.name


        }





    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
binding= ItemHomeGenreListBinding.inflate(LayoutInflater.from(parent.context),parent,false)

    return GenreViewHolder(binding.root)

    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
holder.onBind(differ.currentList[position])
    holder.setIsRecyclable(false)


    }

    override fun getItemCount()=differ.currentList.size


    val differCallBack=object :DiffUtil.ItemCallback<ResponseGnerMovies.ResponseGnerMoviesItem>(){


        override fun areItemsTheSame(
            oldItem: ResponseGnerMovies.ResponseGnerMoviesItem,
            newItem: ResponseGnerMovies.ResponseGnerMoviesItem
        ): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseGnerMovies.ResponseGnerMoviesItem,
            newItem: ResponseGnerMovies.ResponseGnerMoviesItem
        ): Boolean {
return oldItem==newItem



        }


    }
    val differ=AsyncListDiffer(this,differCallBack)
}