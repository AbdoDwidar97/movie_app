package me.dwidar.movieapp.src.app.MainScreen.service.ImplService

import me.dwidar.movieapp.src.app.MainScreen.model.TrendingMoviesResponse
import me.dwidar.movieapp.src.app.MainScreen.service.IService.IMainScreenService
import me.dwidar.movieapp.src.core.network.OnCallResponse
import me.dwidar.movieapp.src.core.network.RetrofitHelper
import retrofit2.await

class ImplMainScreenService : IMainScreenService
{
    val myApi = RetrofitHelper.getInstance()

    override suspend fun getMovies(onCallResponse: OnCallResponse<TrendingMoviesResponse>)
    {
        val response = myApi.getMovies().await()

        if (response.results.isNotEmpty()) onCallResponse.onSuccess(response)
        else onCallResponse.onFail("Empty Result")
    }

}