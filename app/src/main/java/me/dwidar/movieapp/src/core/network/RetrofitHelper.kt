package me.dwidar.movieapp.src.core.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper
{
    private const val baseUrl = "https://api.themoviedb.org/3/"

    fun getInstance(): IApi
    {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
            .create(IApi::class.java)
    }

}