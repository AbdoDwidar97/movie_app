package me.dwidar.movieapp.src.app.MainScreen.viewModel

import android.content.Context
import android.os.Build
import android.view.Gravity
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import java.time.LocalDate

class MainViewModel : ViewModel()
{
    val moviesList : MutableLiveData<ArrayList<MovieListItem>> = MutableLiveData()
    private var loadingLiveData = MutableLiveData<Int>()
    private val mainService = ImplMainScreenService()

    init {
        loadingLiveData.value = ProgressBar.VISIBLE
    }

    fun getMoviesList() : LiveData<ArrayList<MovieListItem>> = moviesList
    fun getLoadingValue() : LiveData<Int> = loadingLiveData

    fun getMovies(context: Context)
    {
        viewModelScope.launch (Dispatchers.IO){

            loadingLiveData.postValue(ProgressBar.VISIBLE)

            mainService.getMovies(object : OnCallResponse<TrendingMoviesResponse> {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onSuccess(result: TrendingMoviesResponse)
                {
                    loadingLiveData.postValue(ProgressBar.INVISIBLE)
                    var movies : ArrayList<MovieListItem> = ArrayList()

                    result.results.forEach {
                        movies.add(MovieListItem(
                            movieYear = LocalDate.parse(it.release_date).year.toString(),
                            movieName = it.original_title,
                            imageUrl = "https://image.tmdb.org/t/p/w500${it.poster_path}",
                            rate = it.vote_average
                        ))
                    }
                    moviesList.postValue(movies)
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