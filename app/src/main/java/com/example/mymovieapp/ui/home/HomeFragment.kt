package com.example.mymovieapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.mymovieapp.databinding.FragmentHomeBinding
import com.example.mymovieapp.ui.home.adapters.LastMoviesAdapter
import com.example.mymovieapp.ui.home.adapters.MoviesGenreAdapter
import com.example.mymovieapp.ui.home.adapters.TopMoviesAdapter
import com.example.mymovieapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var topMovieAdapter: TopMoviesAdapter

    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var genreAdapter: MoviesGenreAdapter


    @Inject
    lateinit var lastMoviesAdapter: LastMoviesAdapter


    private val pagerHelper: PagerSnapHelper by lazy { PagerSnapHelper() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.topMoviesList(3)

        viewModel.genreMovies()
        viewModel.lastMovie()

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.movieslistTop.observe(viewLifecycleOwner) {

            topMovieAdapter.differ.submitList(it.data)



            binding.topMoviesRecycler.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.topMoviesRecycler.adapter = topMovieAdapter

            pagerHelper.attachToRecyclerView(binding.topMoviesRecycler)

            binding.topMoviesIndicator.attachToRecyclerView(binding.topMoviesRecycler, pagerHelper)


        }




        viewModel.genreMovieeeeee.observe(viewLifecycleOwner) { response ->


            genreAdapter.differ.submitList(response)


            binding.genresRecycler.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.genresRecycler.adapter = genreAdapter


        }



        viewModel.progressBarLastMovie.observe(viewLifecycleOwner) {


            if (it) {


                binding.moviesLoading.visibility = View.VISIBLE
                binding.moviesScrollLay.visibility = View.GONE

            } else {
                binding.moviesLoading.visibility = View.GONE
                binding.moviesScrollLay.visibility = View.VISIBLE


            }


        }




        viewModel.lastMovies.observe(viewLifecycleOwner) {

            lastMoviesAdapter.setData(it.data)


            binding.lastMoviesRecycler.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.lastMoviesRecycler.adapter = lastMoviesAdapter


        }



       lastMoviesAdapter.setOnItemClickListener {

           val direction=HomeFragmentDirections.actionToDetail(it.id!!.toInt())

           findNavController().navigate(direction)


       }



        lastMoviesAdapter.setOnItemClickListener {

            val direction=HomeFragmentDirections.actionToDetail(it.id!!.toInt())

            findNavController().navigate(direction)
        }


    }


}