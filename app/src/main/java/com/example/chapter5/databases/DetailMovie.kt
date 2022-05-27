package com.example.chapter5.databases

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.chapter5.databinding.FragmentDetailMovieBinding
import com.example.chapter5.model.MovieViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovie : Fragment() {
    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel by viewModel<MovieViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieViewModel.detailMovie.observe(viewLifecycleOwner){
            //menampilkan detail movie
            binding.apply {
                judulload.text = it.title
                ratingLoad.text = it.voteAverage.toString()
                deskripsiLoad.text = it.overview
                genreLoad.text = it.releaseDate
                Glide.with(binding.imageLoad)
                    .load("https://image.tmdb.org/t/p/w500"+it.posterPath)
                    .into(imageLoad)
            }
        }
        val id = arguments?.getInt("id",-1)
        movieViewModel.getdetail(id!!)

    }

    }
