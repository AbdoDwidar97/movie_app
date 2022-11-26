package me.dwidar.movieapp.src.app.DetailsScreen.model

data class MovieDetailsItem (
    var movieName : String,
    var movieYear : String,
    var rating : Double,
    var imageUrl : String,
    var genres : ArrayList<String>,
    var overview : String,
    var productionCountries : ArrayList<String>,
    var languages : ArrayList<String>
)
