package me.dwidar.movieapp.src.app.DetailsScreen.service.ImplService

import me.dwidar.movieapp.src.app.DetailsScreen.model.MovieDetailsResponse
import me.dwidar.movieapp.src.app.DetailsScreen.service.IService.IDetailsScreenService
import me.dwidar.movieapp.src.core.network.OnCallResponse
import me.dwidar.movieapp.src.core.network.RetrofitHelper
import retrofit2.await

class ImplDetailsScreenService : IDetailsScreenService
{
    val myApi = RetrofitHelper.getInstance()

    override suspend fun getMovieDetails(
        movieID: Int,
        onCallResponse: OnCallResponse<MovieDetailsResponse>
    ) {
        val response = myApi.getMovieDetails(movieID = movieID).await()

        if (response.success == null) onCallResponse.onSuccess(response)
        else onCallResponse.onFail("Result not found")
    }

}