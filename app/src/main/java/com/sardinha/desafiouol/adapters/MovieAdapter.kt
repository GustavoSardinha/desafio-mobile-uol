package com.sardinha.desafiouol.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sardinha.desafiouol.databinding.MovieCardViewBinding
import com.sardinha.desafiouol.models.Movie
import com.sardinha.desafiouol.services.ContentRatingManager
import com.sardinha.desafiouol.ui.description.DescriptionActivity
import com.squareup.picasso.Picasso

class MovieAdapter(private var movieList: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(val binding: MovieCardViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        with(holder.binding) {
            titleText.text = movie.title
            if (movie.images != null) {
                if (movie.images.isNotEmpty()) {
                    Picasso.get()
                        .load(movie.images[0].url)
                        .into(imageMovie)
                }
            }

            if (movie.inPreSale == true) {
                textPreSale.visibility = View.VISIBLE
            } else {
                textPreSale.visibility = View.GONE
            }
            movie.contentRating?.let {
                ContentRatingManager.configureContentRating(imageMovie.context, crText, crCard,
                    it
                )
            }
            if(movie.premiereDate != null){
                premiereText.text = movie.premiereDate.dayAndMonth + "/" + movie.premiereDate.year
            }
            else{
                premiereText.text = ""
            }
            imageMovie.setOnClickListener {
                val intent = Intent(holder.binding.root.context, DescriptionActivity::class.java)
                intent.putExtra("movieData", movie);
                holder.binding.root.context.startActivity(intent)
            }
        }

    }
    fun updateMovieList(newMovieList: List<Movie>) {
        movieList = newMovieList
        notifyDataSetChanged()
    }

    override fun getItemCount() = movieList.size
}
