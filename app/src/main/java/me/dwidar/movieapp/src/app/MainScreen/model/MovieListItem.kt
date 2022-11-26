package me.dwidar.movieapp.src.app.MainScreen.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieListItem (
    @PrimaryKey(autoGenerate = false)
    var movieID : Int,
    var imageUrl : String,
    var movieName : String,
    var movieYear: String,
    var rate : Double
)