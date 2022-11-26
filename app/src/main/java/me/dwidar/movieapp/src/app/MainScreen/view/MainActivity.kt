package me.dwidar.movieapp.src.app.MainScreen.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import me.dwidar.movieapp.databinding.ActivityMainBinding
import me.dwidar.movieapp.databinding.MainActionBarBinding
import me.dwidar.movieapp.src.app.MainScreen.model.MovieListItem
import me.dwidar.movieapp.src.app.MainScreen.model.adapter.MoviesGridAdapter
import me.dwidar.movieapp.src.app.MainScreen.viewModel.MainViewModel

class MainActivity : AppCompatActivity()
{
    lateinit var mainBinding: ActivityMainBinding
    private lateinit var customActionBarBinding: MainActionBarBinding
    private lateinit var mainViewModel : MainViewModel
    private lateinit var moviesGridAdapter : MoviesGridAdapter
    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        customActionBarBinding = MainActionBarBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        setSupportActionBar(customActionBarBinding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        configureMoviesGridView()

        mainViewModel.getMoviesList().observe(this){
            updateMoviesGridView(it)
        }

        mainViewModel.getLoadingValue().observe(this){
            updateLoadingValue(it)
        }

        getMovies()
    }

    private fun configureMoviesGridView()
    {
        gridLayoutManager = GridLayoutManager(applicationContext, 2, LinearLayoutManager.VERTICAL, false)
        mainBinding.moviesGrid.layoutManager = gridLayoutManager
    }

    private fun updateMoviesGridView(movies : ArrayList<MovieListItem>)
    {
        moviesGridAdapter = MoviesGridAdapter(movies)
        mainBinding.moviesGrid.adapter = moviesGridAdapter
    }

    private fun updateLoadingValue(value : Int)
    {
        mainBinding.loadingBar.visibility = value
    }

    private fun getMovies()
    {
        mainViewModel.getMovies(this)
    }
}