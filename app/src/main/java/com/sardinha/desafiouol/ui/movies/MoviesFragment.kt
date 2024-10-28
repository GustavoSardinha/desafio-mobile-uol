package com.sardinha.desafiouol.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sardinha.desafiouol.adapters.MovieAdapter
import com.sardinha.desafiouol.databinding.FragmentMoviesBinding
import com.sardinha.desafiouol.models.Movie
import com.sardinha.desafiouol.services.MovieListManager

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(MoviesViewModel::class.java)

        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val movieList = MovieListManager.getMovieList(0, MovieListManager.getSize() - 1)
        val movieAdapter = configureMovieGridView(binding.recyclerView
            , movieList)
        binding.imageButton2.setOnClickListener{
            var filteredList = MovieListManager.filterMoviesByTitle(binding.editTextText.text.toString())
            if(filteredList != null){
                movieAdapter.updateMovieList(filteredList)
            }
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
