package com.sardinha.desafiouol.services

import android.util.Log
import com.sardinha.desafiouol.models.Movie
import com.sardinha.desafiouol.models.MovieAPIResponse
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MovieListManager {

    companion object {
        private var moviesList: List<Movie>? = null
        private val retrofit by lazy {
            RetrofitHelper.retrofit
        }

        suspend fun loadMovies() {
            try {
                val moviesApi = retrofit.create(MovieAPI::class.java)
                val moviesData: Response<MovieAPIResponse> = moviesApi.loadMovies()

                if (moviesData.isSuccessful) {
                    moviesList = moviesData.body()?.items
                    Log.i("load_movies", "Sucesso ao carregar os dados!")
                } else {
                    Log.i("load_movies", "Erro ao carregar os filmes!")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("load_movies", e.toString())
            }
        }


        fun getMovieList(firstPos: Int, finalPos: Int): List<Movie> {
            if (moviesList != null && firstPos >= 0 && finalPos <= moviesList!!.size && firstPos < finalPos) {
                return moviesList!!.subList(firstPos, finalPos)
            } else {
                return emptyList()
            }
        }
        fun getSize(): Int{
            return moviesList!!.size
        }
        fun orderByDate(): List<Movie> {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val sortedMovieList = moviesList
                ?.filter { it.premiereDate?.dayAndMonth != null } // Filtra os nulos
                ?.sortedBy{ movie ->
                    dateFormat.parse("${movie.premiereDate?.dayAndMonth}/${movie.premiereDate?.year}")
                }
            return sortedMovieList ?: emptyList()
        }
        fun filterByMonth(month: Int): List<Movie> {
            Log.i("load_movies", month.toString())
            if(month in 13..-1){
                return emptyList()
            }
            if(month == 0){
                return moviesList?.filter { it.premiereDate?.dayAndMonth != null }!!
            }
            try {
                val filterMovieList = moviesList
                    ?.filter { it.premiereDate?.dayAndMonth != null }
                    ?.filter { (it.premiereDate?.dayAndMonth?.split("/")!![1]).toInt() == month}
                filterMovieList?.forEach{ movie ->
                    Log.i("load_movies",
                        (movie.premiereDate?.dayAndMonth?.split("/")!![1]).toInt().toString())
                }
                return filterMovieList!!
            }
            catch (e: Exception){
                Log.i("load_movies", "Erro ao carregar!")
            }
            return emptyList()
        }
        fun filterMoviesByTitle(query: String): List<Movie> {
            return moviesList!!.filter { movie ->
                movie.title.contains(query, ignoreCase = true)
            }
        }
        fun filterHorizontalImage(): List<Movie> {
            return moviesList?.filter { movie ->
                val images = movie.images
                images != null && images.size > 1 && images[1].type == "PosterHorizontal"
            } ?: emptyList()
        }
    }
}
