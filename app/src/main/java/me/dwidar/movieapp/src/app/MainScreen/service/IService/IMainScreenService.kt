package me.dwidar.movieapp.src.app.MainScreen.service.IService
import me.dwidar.movieapp.src.app.MainScreen.model.TrendingMoviesResponse
import me.dwidar.movieapp.src.core.network.OnCallResponse

interface IMainScreenService
{
    suspend fun getMovies(onCallResponse: OnCallResponse<TrendingMoviesResponse>)
}