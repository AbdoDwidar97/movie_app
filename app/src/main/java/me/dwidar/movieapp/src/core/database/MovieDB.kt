package me.dwidar.movieapp.src.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.dwidar.movieapp.src.app.MainScreen.model.MovieListItem

@Database(entities = [MovieListItem::class], version = 1, exportSchema = false)
abstract class MovieDB : RoomDatabase()
{
    abstract fun movieDao() : MovieDao

    companion object
    {
        @Volatile
        private var INSTANCE : MovieDB? = null

        fun getDatabase(context: Context) : MovieDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDB::class.java,
                    "movie_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}