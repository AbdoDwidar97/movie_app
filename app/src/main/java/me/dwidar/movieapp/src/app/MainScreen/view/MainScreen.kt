package me.dwidar.movieapp.src.app.MainScreen.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import me.dwidar.movieapp.R
import me.dwidar.movieapp.databinding.FragmentMainScreenBinding
import me.dwidar.movieapp.databinding.MainActionBarBinding
import me.dwidar.movieapp.src.app.MainScreen.model.MovieListItem
import me.dwidar.movieapp.src.app.MainScreen.model.adapter.MoviesGridAdapter
import me.dwidar.movieapp.src.app.MainScreen.model.adapter.setOnMovieItemClickListener
import me.dwidar.movieapp.src.app.MainScreen.viewModel.MainViewModel

class MainScreen : Fragment()
{
    lateinit var mainScreenBinding: FragmentMainScreenBinding
    private lateinit var mainViewModel : MainViewModel
    private lateinit var moviesGridAdapter : MoviesGridAdapter
    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        // Inflate the layout for this fragment
        mainScreenBinding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return mainScreenBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        configureMoviesGridView()

        mainViewModel.getMoviesList().observe(viewLifecycleOwner){
            updateMoviesGridView(it)
        }

        mainViewModel.getLoadingValue().observe(viewLifecycleOwner){
            updateLoadingValue(it)
        }

        getMovies()

        mainScreenBinding.moviesGrid.setOnClickListener {
            findNavController().navigate(R.id.detailsScreen)
        }
    }

    private fun configureMoviesGridView()
    {
        gridLayoutManager = GridLayoutManager(activity, 2, LinearLayoutManager.VERTICAL, false)
        mainScreenBinding.moviesGrid.layoutManager = gridLayoutManager
    }

    private fun updateMoviesGridView(movies : ArrayList<MovieListItem>)
    {
        moviesGridAdapter = MoviesGridAdapter(movies, object : setOnMovieItemClickListener{
            override fun onClick(movieID: Int) {
                findNavController().navigate(R.id.detailsScreen)
            }
        })
        mainScreenBinding.moviesGrid.adapter = moviesGridAdapter
    }

    private fun updateLoadingValue(value : Int)
    {
        mainScreenBinding.loadingBar.visibility = value
    }

    private fun getMovies()
    {
        mainViewModel.getMovies(requireActivity().applicationContext)
    }
}