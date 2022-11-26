package me.dwidar.movieapp.src.app.DetailsScreen.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import me.dwidar.movieapp.R
import me.dwidar.movieapp.databinding.FragmentDetailsScreenBinding
import me.dwidar.movieapp.databinding.MainActionBarBinding
import me.dwidar.movieapp.src.app.DetailsScreen.viewModel.DetailsViewModel

class DetailsScreen : Fragment()
{
    private lateinit var detailsBinding: FragmentDetailsScreenBinding
    private lateinit var detailsViewModel: DetailsViewModel
    private var movieID : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        detailsBinding = FragmentDetailsScreenBinding.inflate(inflater, container, false)
        return detailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        movieID = arguments?.getInt("movieID")!!
        detailsViewModel = ViewModelProvider(this)[DetailsViewModel::class.java]

        detailsViewModel.getLoadingValue().observe(viewLifecycleOwner)
        {
            if (it == ProgressBar.VISIBLE) detailsBinding.detailsLayout.visibility = ConstraintLayout.INVISIBLE
            else detailsBinding.detailsLayout.visibility = ConstraintLayout.VISIBLE

            detailsBinding.loadingDetails.visibility = it
        }

        detailsViewModel.getMovieDetails().observe(viewLifecycleOwner)
        {
            detailsBinding.movieNameDetails.text = it.movieName
            detailsBinding.movieYearDetails.text = it.movieYear
            detailsBinding.movieRateDetails.text = it.rating.toString()
            detailsBinding.genresDetails.text = it.genres.run {
                var value = ""

                this.forEach { str ->

                    value += "$str . "
                }

                value = value.trim()
                value.substring(0, value.length - 1)
            }
            detailsBinding.overviewDetails.text = it.overview
            detailsBinding.productionCountriesDetails.text = it.productionCountries.run {
                var value = ""

                this.forEach { str ->

                    value += "$str . "
                }

                value = value.trim()
                value.substring(0, value.length - 1)
            }
            detailsBinding.languagesDetails.text = it.languages.run {
                var value = ""

                this.forEach { str ->

                    value += "$str . "
                }

                value = value.trim()
                value.substring(0, value.length - 1)
            }

            Picasso.get().load(it.imageUrl)
                .placeholder(R.mipmap.img_placeholder)
                .error(R.mipmap.img_placeholder)
                .into(detailsBinding.movieImgDetails)
        }

        detailsViewModel.getDetails(context = requireContext(), movieID = movieID)
    }

}