package ie.setu.moviereview_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ie.setu.moviereview_app.R
import ie.setu.moviereview_app.databinding.ActivityMoviereviewListBinding
import ie.setu.moviereview_app.databinding.ListcardPlacemarkBinding
import ie.setu.moviereview_app.main.MainApp
import ie.setu.moviereview_app.models.MoviereviewModel

class MoviereviewListActivity : AppCompatActivity() {


    lateinit var app: MainApp
    private lateinit var binding: ActivityMoviereviewListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviereviewListBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_moviereview_list)
        setContentView(binding.root)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = MoviereviewAdapter(app.movieReview)
    }
}

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