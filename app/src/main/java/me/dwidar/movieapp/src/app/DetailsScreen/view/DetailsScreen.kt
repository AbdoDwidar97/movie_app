package me.dwidar.movieapp.src.app.DetailsScreen.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import me.dwidar.movieapp.R
import me.dwidar.movieapp.databinding.FragmentDetailsScreenBinding
import me.dwidar.movieapp.databinding.MainActionBarBinding

class DetailsScreen : Fragment()
{
    lateinit var detailsBinding: FragmentDetailsScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        detailsBinding = FragmentDetailsScreenBinding.inflate(inflater, container, false)
        return detailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsBinding.loadingDetails.visibility = ProgressBar.INVISIBLE
    }

}