package com.example.mymovieapp.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovieapp.databinding.FragmentFavoriteBinding
import com.example.mymovieapp.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentFavoriteBinding


    @Inject
    lateinit var favoriteAdapter: FavoriteAdapter







    private val viewModel: FavoriteViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.favoriteMovie()


        viewModel.listOfMovie.observe(viewLifecycleOwner) {

            favoriteAdapter.setData(it)

            binding.favoriteRecycler.adapter = favoriteAdapter

            binding.favoriteRecycler.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        }

        viewModel.emptyList.observe(viewLifecycleOwner){

            if (it){

                binding.favoriteRecycler.visibility=View.GONE
                binding.emptyItemsLay.visibility=View.VISIBLE


            }else{

                binding.favoriteRecycler.visibility=View.VISIBLE
                binding.emptyItemsLay.visibility=View.GONE

            }



        }



        favoriteAdapter.setOnItemClickListener {


val direction=FavoriteFragmentDirections.actionToDetail(it.id.toInt())

            findNavController().navigate(direction)


        }


    }


}