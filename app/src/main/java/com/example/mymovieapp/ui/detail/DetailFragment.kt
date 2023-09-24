package com.example.mymovieapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.mymovieapp.R
import com.example.mymovieapp.databinding.FragmentDetailBinding
import com.example.mymovieapp.db.MovieEntity
import com.example.mymovieapp.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentDetailBinding

    @Inject
    lateinit var adapter: DetailAdapter

    private val vieModel: DetailViewModel by viewModels()

    private val args: DetailFragmentArgs by navArgs()

    @Inject
    lateinit var entity: MovieEntity

    private var movieId = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieId = args.movieId

        if (movieId > 0) {

            vieModel.loadMoviesApi(movieId)


        }


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        vieModel.progressBarLoading.observe(viewLifecycleOwner) {

            if (it) {

                binding.detailLoading.visibility = View.VISIBLE

                binding.detailScrollView.visibility = View.GONE

            } else {


                binding.detailLoading.visibility = View.GONE


                binding.detailScrollView.visibility = View.VISIBLE
            }


        }


        vieModel.listOfMovies.observe(viewLifecycleOwner) { response ->

            binding.apply {


                posterBigImg.load(response.poster)
                posterNormalImg.load(response.poster) {
                    crossfade(true)
                    crossfade(800)
                }
                movieNameTxt.text = response.title
                movieRateTxt.text = response.imdbRating
                movieTimeTxt.text = response.runtime
                movieDateTxt.text = response.released
                movieSummaryInfo.text = response.plot
                movieActorsInfo.text = response.actors


                adapter.differ.submitList(response.images)

                imagesRecyclerView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                imagesRecyclerView.adapter = adapter


            }




            binding.favImg.setOnClickListener {


                entity.id = movieId
                entity.poster = response.poster.toString()

                entity.year = response.year.toString()
                entity.rate = response.rated.toString()
                entity.name = response.title.toString()

                entity.country = response.country.toString()


                vieModel.favoriteMoviesDb(movieId, entity)


            }

        }




        lifecycleScope.launchWhenCreated {


            if (vieModel.existInDatabase(movieId)) {


                binding.favImg.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.scarlet
                    )
                )


            } else {

                binding.favImg.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.philippineSilver
                    )
                )


            }


        }




        vieModel.isInDatabase.observe(viewLifecycleOwner) {

            if (it) {

                binding.favImg.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.scarlet
                    )
                )


            } else {

                binding.favImg.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.philippineSilver
                    )
                )


            }


        }




















        binding.backImg.setOnClickListener {

            findNavController().navigateUp()


        }


    }


}