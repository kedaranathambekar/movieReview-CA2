package ie.setu.moviereview_app.views.moviereviewlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ie.setu.moviereview_app.adapters.MoviereviewListener
import ie.setu.moviereview_app.databinding.ListcardPlacemarkBinding
import ie.setu.moviereview_app.models.MoviereviewModel

interface MoviereviewListener {
    fun onMoviereviewClick(movieReview: MoviereviewModel, position : Int)
}
    

class MoviereviewAdapter constructor(private var moviereviews: List<MoviereviewModel>,
                                     private val listener: MoviereviewListener
) :
    RecyclerView.Adapter<MoviereviewAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = ListcardPlacemarkBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val moviereview = moviereviews[holder.adapterPosition]
        holder.bind(moviereview,listener)
    }

    override fun getItemCount(): Int = moviereviews.size

    class MainHolder(private val binding: ListcardPlacemarkBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(moviereview: MoviereviewModel, listener: MoviereviewListener) {
            binding.movieName.text = moviereview.tittle
            binding.movieDescription.text = moviereview.description
            binding.Reviewer.text = moviereview.reviewer
            Picasso.get().load(moviereview.image).resize(200,200).into(binding.imageIcon)
            binding.root.setOnClickListener { listener.onMoviereviewClick(moviereview,adapterPosition) }
        }
    }
}
