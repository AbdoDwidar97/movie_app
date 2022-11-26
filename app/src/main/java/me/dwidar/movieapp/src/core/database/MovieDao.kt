package me.dwidar.movieapp.src.core.database
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.dwidar.movieapp.src.app.MainScreen.model.MovieListItem

@Dao
interface MovieDao
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movieListItem: MovieListItem)

    @Query("SELECT * FROM movie_table")
    fun readAllMovies() : LiveData<ArrayList<MovieListItem>>

    @Query("DELETE FROM movie_table")
    suspend fun deleteMovies()
}