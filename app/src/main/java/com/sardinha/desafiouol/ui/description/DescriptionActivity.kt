package com.sardinha.desafiouol.ui.description

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.sardinha.desafiouol.databinding.ActivityDescriptionBinding
import com.sardinha.desafiouol.models.Movie
import com.sardinha.desafiouol.services.ContentRatingManager
import com.squareup.picasso.Picasso

class DescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDescriptionBinding
    private lateinit var movie: Movie // Variável para armazenar o filme

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movie = intent.getParcelableExtra("movieData")!!
        configureMovieData(movie)
        binding.closeButton.setOnClickListener {
            finish()
        }
    }
    private fun configureMovieData(movie: Movie){
        with(binding) {
            textTitleDesc.text = movie.title
            if (movie.synopsis != null) {
                textSpynopsDesc.text = movie.synopsis
            } else {
                textSpynopsDesc.text = "Sem descrição."
            }
            if (movie.images != null) {
                if (movie.images!!.isNotEmpty()) {
                    Picasso.get()
                        .load(movie.images!![0].url)
                        .into(imageMovieDesc)
                }
            }
            if (movie.duration != null) {
                textDurationDesc.text = movie.duration + "min"
            } else {
                textDurationDesc.text = "Sem informações."
            }
            movie.contentRating?.let {
                ContentRatingManager.configureContentRating(crCardDesc.context, textCrDesc, crCardDesc,
                    it
                )
            }
            if (movie.genres != null) {
                var temp = ""
                movie.genres.forEach{genre ->
                    temp += genre + " "
                }
                textGenreDesc.text = temp
            } else {
                textGenreDesc.text = "Sem informações."
            }
            if (movie.premiereDate != null) {
                textPremiereDesc.text = movie.premiereDate.dayAndMonth + "/" + movie.premiereDate.year
            } else {
                textPremiereDesc.text = "Sem informações."
            }
        }
    }
}
