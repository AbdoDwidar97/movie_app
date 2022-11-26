package me.dwidar.movieapp.src.core.network

import me.dwidar.movieapp.src.app.DetailsScreen.model.MovieDetailsResponse
import me.dwidar.movieapp.src.app.MainScreen.model.TrendingMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IApi
{
    @GET("trending/movie/day?api_key=c50f5aa4e7c95a2a553d29b81aad6dd0")
    fun getMovies() : Call<TrendingMoviesResponse>

    @GET("movie/{movieID}?api_key=c50f5aa4e7c95a2a553d29b81aad6dd0")
    fun getMovieDetails(@Path("movieID") movieID : Int) : Call<MovieDetailsResponse>
}