package me.dwidar.movieapp.src.app

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

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        customActionBarBinding = MainActionBarBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        setSupportActionBar(customActionBarBinding.root)
    }
}