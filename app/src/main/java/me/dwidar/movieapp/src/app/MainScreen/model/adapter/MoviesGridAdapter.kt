package me.dwidar.movieapp.src.app.MainScreen.model.adapter
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import me.dwidar.movieapp.R
import me.dwidar.movieapp.databinding.MovieListItemBinding
import me.dwidar.movieapp.src.app.MainScreen.model.MovieListItem

class MoviesGridAdapter(private val moviesList : List<MovieListItem>) : RecyclerView.Adapter<MoviesGridAdapter.MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        val currentMovie = moviesList[position]
        holder.bindItem(currentMovie)
        holder.itemBinding.cardItem.setOnClickListener {
            Log.d("Movie", "name: ${currentMovie.imageName}")
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    class MyViewHolder(val itemBinding: MovieListItemBinding) : RecyclerView.ViewHolder(itemBinding.root)
    {
        fun bindItem(movieListItem: MovieListItem)
        {
            itemBinding.movieName.text = movieListItem.imageName
            itemBinding.movieYear.text = movieListItem.movieYear

            Picasso.get().load(movieListItem.imageUrl)
                .placeholder(R.mipmap.img_placeholder)
                .error(R.mipmap.img_placeholder)
                .into(itemBinding.movieImg)

        }
    }

}