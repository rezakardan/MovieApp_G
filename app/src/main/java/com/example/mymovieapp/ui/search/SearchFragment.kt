package com.example.mymovieapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovieapp.databinding.FragmentSearchBinding
import com.example.mymovieapp.ui.home.adapters.LastMoviesAdapter
import com.example.mymovieapp.utils.initRecycler
import com.example.mymovieapp.utils.showInvisible
import com.example.mymovieapp.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentSearchBinding

    private val viewModel: SearchViewModel by viewModels()


    @Inject
    lateinit var searchAdapter: SearchAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchEdt.addTextChangedListener {

            val search = it.toString()

            if (search.isNotEmpty()) {


                viewModel.searching(search)


            }


        }




        viewModel.movieList.observe(viewLifecycleOwner) {


            searchAdapter.setData(it.data)

            binding.moviesRecycler.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.moviesRecycler.adapter = searchAdapter

        }



        viewModel.empty.observe(viewLifecycleOwner) {

            if (it) {
                binding.moviesRecycler.visibility = View.GONE

                binding.emptyItemsLay.visibility = View.VISIBLE


            } else {

                binding.moviesRecycler.visibility = View.VISIBLE

                binding.emptyItemsLay.visibility = View.GONE

            }


        }



        viewModel.progressLoading.observe(viewLifecycleOwner){


            if (it){

                binding.searchLoading.visibility=View.VISIBLE






            }else{

                binding.searchLoading.visibility=View.GONE



            }



        }

        searchAdapter.setOnItemClickListener {

            val direction=SearchFragmentDirections.actionToDetail(it.id!!.toInt())

            findNavController().navigate(direction)


        }






    }


}