package me.dwidar.movieapp.src.app.DetailsScreen.service.IService

import me.dwidar.movieapp.src.app.DetailsScreen.model.MovieDetailsResponse
import me.dwidar.movieapp.src.core.network.OnCallResponse

interface IDetailsScreenService
{
    suspend fun getMovieDetails(movieID : Int, onCallResponse: OnCallResponse<MovieDetailsResponse>)
}