package ie.setu.moviereview_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.setu.moviereview_app.databinding.ListcardPlacemarkBinding
import ie.setu.moviereview_app.models.MoviereviewModel

class MoviereviewAdapter constructor(private var moviereviews: List<MoviereviewModel>) :
    RecyclerView.Adapter<MoviereviewAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = ListcardPlacemarkBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val placemark = moviereviews[holder.adapterPosition]
        holder.bind(placemark)
    }

    override fun getItemCount(): Int = moviereviews.size

    class MainHolder(private val binding: ListcardPlacemarkBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(moviereview: MoviereviewModel) {
            binding.movieName.text = moviereview.tittle
            binding.movieDescription.text = moviereview.description
            binding.Reviewer.text = moviereview.reviewer
        }
    }
}