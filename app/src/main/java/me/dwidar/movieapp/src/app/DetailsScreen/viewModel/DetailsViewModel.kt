package me.dwidar.movieapp.src.app.DetailsScreen.viewModel

import android.app.Application
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.dwidar.movieapp.src.app.DetailsScreen.model.MovieDetailsItem
import me.dwidar.movieapp.src.app.DetailsScreen.model.MovieDetailsResponse
import me.dwidar.movieapp.src.app.DetailsScreen.service.IService.IDetailsScreenService
import me.dwidar.movieapp.src.app.DetailsScreen.service.ImplService.ImplDetailsScreenService
import me.dwidar.movieapp.src.app.MainScreen.service.IService.IMainScreenService
import me.dwidar.movieapp.src.core.network.OnCallResponse
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel
    @Inject
    constructor(private val application: Application, private val detailsService: IDetailsScreenService) : ViewModel()
{
    private var loadingLiveData = MutableLiveData<Int>()
    private var movieDetails = MutableLiveData<MovieDetailsItem>()
    // private var detailsService = ImplDetailsScreenService()

    fun getLoadingValue() : LiveData<Int> = loadingLiveData
    fun getMovieDetails() : LiveData<MovieDetailsItem> = movieDetails

    init {
        loadingLiveData.value = ProgressBar.VISIBLE
    }

    fun getDetails(context: Context, movieID : Int)
    {
        viewModelScope.launch (Dispatchers.IO){

            loadingLiveData.postValue(ProgressBar.VISIBLE)

            detailsService.getMovieDetails(movieID = movieID, object : OnCallResponse<MovieDetailsResponse> {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onSuccess(result: MovieDetailsResponse)
                {
                    loadingLiveData.postValue(ProgressBar.INVISIBLE)
                    var movieDetailsResponse = MovieDetailsItem(
                        movieName = result.original_title,
                        movieYear = LocalDate.parse(result.release_date).year.toString(),
                        rating = result.vote_average,
                        imageUrl = "https://image.tmdb.org/t/p/w500${result.poster_path}",
                        genres = result.genres.run {
                            var gnrs = ArrayList<String>()
                            forEach {
                                gnrs.add(it.name)
                            }

                            gnrs
                        },
                        overview = result.overview,
                        productionCountries = result.production_countries.run {
                            var countries = ArrayList<String>()
                            forEach {
                                countries.add(it.name)
                            }

                            countries
                        },
                        languages = result.spoken_languages.run {
                            var langs = ArrayList<String>()
                            forEach {
                                langs.add(it.name)
                            }

                            langs
                        },
                    )

                    movieDetails.postValue(movieDetailsResponse)
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