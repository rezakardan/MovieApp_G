package com.example.mymovieapp.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mymovieapp.databinding.ItemDetailImagesBinding
import javax.inject.Inject

class DetailAdapter @Inject constructor():RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    lateinit var binding: ItemDetailImagesBinding

    inner class DetailViewHolder(item:View):RecyclerView.ViewHolder(item){

        fun onBind(item:String){


            binding.itemImages.load(item){


                crossfade(800)
                crossfade(true)
            }


        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
binding=  ItemDetailImagesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    return DetailViewHolder(binding.root)}

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.onBind(differ.currentList[position])
holder.setIsRecyclable(false)    }

    override fun getItemCount()=differ.currentList.size




    val differCallBack=object :(DiffUtil.ItemCallback<String>)(){
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem==newItem
        }


    }
    val differ=AsyncListDiffer(this,differCallBack)
}