package com.sardinha.desafiouol.services

// RetrofitHelper.kt
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val BASE_URL = "https://api-content.ingresso.com/v0/events/coming-soon/partnership/"

class RetrofitHelper {


    companion object{
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
