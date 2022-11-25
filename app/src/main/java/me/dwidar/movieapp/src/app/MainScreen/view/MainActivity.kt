package me.dwidar.movieapp.src.app.MainScreen.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import me.dwidar.movieapp.databinding.ActivityMainBinding
import me.dwidar.movieapp.databinding.MainActionBarBinding
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

        gridLayoutManager = GridLayoutManager(applicationContext, 2, LinearLayoutManager.VERTICAL, false)
        mainBinding.moviesGrid.layoutManager = gridLayoutManager

        mainViewModel.getMoviesList().observe(this){
            moviesGridAdapter = MoviesGridAdapter(it)
            mainBinding.moviesGrid.adapter = moviesGridAdapter
        }

    }
}