package me.dwidar.movieapp.src.core.database

import me.dwidar.movieapp.src.app.MainScreen.model.MovieListItem

class MovieRepo(private val movieDao: MovieDao)
{
    val allMovies = movieDao.readAllMovies()

    suspend fun addMovie(movieListItem: MovieListItem)
    {
        movieDao.addMovie(movieListItem)
    }

    suspend fun deleteAll()
    {
        movieDao.deleteMovies()
    }
}