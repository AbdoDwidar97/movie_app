package me.dwidar.movieapp.src.app.MainScreen.viewModel

import android.content.Context
import android.view.Gravity
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.dwidar.movieapp.src.app.MainScreen.model.MovieListItem
import me.dwidar.movieapp.src.app.MainScreen.model.TrendingMoviesResponse
import me.dwidar.movieapp.src.app.MainScreen.service.IService.IMainScreenService
import me.dwidar.movieapp.src.app.MainScreen.service.ImplService.ImplMainScreenService
import me.dwidar.movieapp.src.core.network.OnCallResponse

class MainViewModel : ViewModel()
{
    val moviesList : MutableLiveData<List<MovieListItem>> = MutableLiveData()
    private var loadingLiveData = MutableLiveData<Int>()
    private val mainService = ImplMainScreenService()

    init {
        moviesList.value = listOf(
            MovieListItem(
                imageName = "Black Adam",
                imageUrl = "https://image.tmdb.org/t/p/w500/bQXAqRx2Fgc46uCVWgoPz5L5Dtr.jpg",
                rate = 7.4,
                movieYear = "2022"
            ),

            MovieListItem(
                imageName = "Black Adam",
                imageUrl = "https://image.tmdb.org/t/p/w500/bQXAqRx2Fgc46uCVWgoPz5L5Dtr.jpg",
                rate = 7.4,
                movieYear = "2022"
            ),

            MovieListItem(
                imageName = "Black Adam",
                imageUrl = "https://image.tmdb.org/t/p/w500/bQXAqRx2Fgc46uCVWgoPz5L5Dtr.jpg",
                rate = 7.4,
                movieYear = "2022"
            ),

            MovieListItem(
                imageName = "Black Adam",
                imageUrl = "https://image.tmdb.org/t/p/w500/bQXAqRx2Fgc46uCVWgoPz5L5Dtr.jpg",
                rate = 7.4,
                movieYear = "2022"
            ),

            MovieListItem(
                imageName = "Black Adam",
                imageUrl = "https://image.tmdb.org/t/p/w500/bQXAqRx2Fgc46uCVWgoPz5L5Dtr.jpg",
                rate = 7.4,
                movieYear = "2022"
            ),

            MovieListItem(
                imageName = "Black Adam",
                imageUrl = "https://image.tmdb.org/t/p/w500/bQXAqRx2Fgc46uCVWgoPz5L5Dtr.jpg",
                rate = 7.4,
                movieYear = "2022"
            ),
        )
    }

    fun getMoviesList() : LiveData<List<MovieListItem>> = moviesList

    fun getMovies(context: Context)
    {
        viewModelScope.launch (Dispatchers.IO){

            loadingLiveData.postValue(ProgressBar.VISIBLE)

            mainService.getMovies(object : OnCallResponse<TrendingMoviesResponse> {
                override fun onSuccess(result: TrendingMoviesResponse)
                {
                    /// here we must fill moviesList from the response ...
                    loadingLiveData.postValue(ProgressBar.INVISIBLE)
                    // moviesList.postValue(result)
                }

                override fun onFail(errorMessage: String)
                {
                    loadingLiveData.postValue(ProgressBar.INVISIBLE)
                    runBlocking (Dispatchers.Main){
                        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).apply {
                            this.setGravity(Gravity.TOP, 0, 20)
                            this.show()
                        }
                    }
                }
            })
        }
    }

}