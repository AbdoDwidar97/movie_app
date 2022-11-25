package me.dwidar.movieapp.src.app.MainScreen.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.dwidar.movieapp.src.app.MainScreen.model.MovieListItem

class MainViewModel : ViewModel()
{
    val moviesList : MutableLiveData<List<MovieListItem>> = MutableLiveData()

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

}