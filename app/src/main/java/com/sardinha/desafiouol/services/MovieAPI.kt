package com.sardinha.desafiouol.services

import com.sardinha.desafiouol.models.MovieAPIResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieAPI {
    @GET("desafio")
    suspend fun loadMovies(): Response<MovieAPIResponse>
}
