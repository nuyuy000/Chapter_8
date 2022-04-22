package com.example.chapter5.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.chapter5.R
import com.example.chapter5.databases.ResultX
import com.example.chapter5.databinding.FragmentListFilmBinding
import com.example.chapter5.model.MovieAdapter
import com.example.chapter5.model.MovieViewModel

class ListFilm : Fragment() {
    private var _binding: FragmentListFilmBinding? = null
    private val binding get() = _binding!!
    lateinit var movieViewModel: MovieViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieViewModel = ViewModelProvider(requireActivity())[MovieViewModel::class.java]
        movieViewModel.dataMovie.observe(viewLifecycleOwner){
            showlistmovie(it.results)
        }
        binding.btnprofil.setOnClickListener{
            findNavController().navigate(R.id.action_listFilm_to_updateProfile)
        }
    }
    private fun showlistmovie(results: List<ResultX>) {
        val adapter= MovieAdapter{
            val bundle = bundleOf("id" to it.id)
           findNavController().navigate(R.id.action_listFilm_to_detailMovie,bundle)
        }
        adapter.submitList(results)
        binding.reycycleview1.adapter=adapter
        binding.reycycleview2.adapter=adapter
        binding.reycycleview3.adapter=adapter
    }

//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//        binding.btnprofil.setOnClickListener{
//            it.findNavController().navigate(R.id.action_listFilm_to_updateProfile)
//        }
    }



