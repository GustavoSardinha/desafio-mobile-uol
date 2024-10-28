package com.sardinha.desafiouol.ui.premiere

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sardinha.desafiouol.adapters.MovieAdapter
import com.sardinha.desafiouol.databinding.FragmentPremiereBinding
import com.sardinha.desafiouol.models.Movie
import com.sardinha.desafiouol.services.MovieListManager
import android.widget.ArrayAdapter
import com.sardinha.desafiouol.R

class PremiereFragment : Fragment() {
    private var _binding: FragmentPremiereBinding? = null
    private val binding get() = _binding!!
    private val months = listOf( "Todos",
        "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
        "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val premiereViewModel =
            ViewModelProvider(this).get(PremiereViewModel::class.java)

        _binding = FragmentPremiereBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, months)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        binding.spinner.adapter = adapter

        val movieList = MovieListManager.orderByDate()
        val movieAdapter = configureMovieGridView(binding.recyclerView, movieList)
        binding.imageButton.setOnClickListener{
            val filteredMovies = MovieListManager.filterByMonth(binding.spinner.selectedItemPosition)
            movieAdapter.updateMovieList(filteredMovies)
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun configureMovieGridView(recycler: RecyclerView, movieList: List<Movie>): MovieAdapter {
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        val movieAdapter = MovieAdapter(movieList)
        recycler.adapter = movieAdapter
        return movieAdapter
    }
}


