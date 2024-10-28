package com.sardinha.desafiouol.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sardinha.desafiouol.adapters.MovieAdapter
import com.sardinha.desafiouol.databinding.FragmentHomeBinding
import com.sardinha.desafiouol.models.Movie
import com.sardinha.desafiouol.services.ContentRatingManager
import com.sardinha.desafiouol.services.MovieListManager
import com.sardinha.desafiouol.ui.description.DescriptionActivity
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Carregar a lista de filmes
        var movieList1 = MovieListManager.getMovieList(0, 10)
        connfigureMovieHorizontaView( binding.recyclerView1, movieList1)
        var movieList2 = MovieListManager.getMovieList(11, 21)
        connfigureMovieHorizontaView( binding.recyclerView2, movieList2)
        var horizontalPosterMovies = MovieListManager.filterHorizontalImage()
        configurePosterHorizontal(horizontalPosterMovies)
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun configurePosterHorizontal(moviesList: List<Movie>){
        if(moviesList.size > 3){
            configureImage(moviesList[1], binding.bigImageMovie, binding.bigTitleMovieText)
            var genre = ""
            moviesList[0].genres!!.forEach{ genre
                genre += "$genre "
            }
            binding.infoBigText.text = genre
            configureImage(moviesList[2], binding.miniImageMovieL, binding.miniTitleMovieL)
            configureImage(moviesList[3], binding.miniImageMovieR, binding.miniTitleMovieR)
        }
    }
    private fun configureImage(movie: Movie, imageView: ImageView, textView: TextView){
        Picasso.get()
            .load(movie.images?.get(1)?.url)
            .into(binding.miniImageMovieL)
        textView.text = movie.title
        imageView.setOnClickListener {
            val intent = Intent(imageView.context, DescriptionActivity::class.java)
            intent.putExtra("movieData", movie);
            startActivity(intent)
        }
    }
    private fun connfigureMovieHorizontaView(recycler : RecyclerView, movieList : List<Movie>){
        recycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = MovieAdapter(movieList)
    }
}