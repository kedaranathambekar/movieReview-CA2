package ie.setu.moviereview_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import ie.setu.moviereview_app.R
import ie.setu.moviereview_app.databinding.ActivityMoviereviewBinding
import ie.setu.moviereview_app.models.MoviereviewModel
import timber.log.Timber
import timber.log.Timber.i


class MoviereviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviereviewBinding

    var movieReview = MoviereviewModel()
    var movieReviews = ArrayList<MoviereviewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviereviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_moviereview)

        Timber.plant(Timber.DebugTree())
        i("Moviereview Activity starterd.. ")

        binding.btnAdd.setOnClickListener() {

            movieReview.tittle = binding.moviereviewTitle.text.toString()
            movieReview.description = binding.movieReviewDescription.text.toString()
            movieReview.reviewer = binding.movieReviewReviewname.text.toString()
            if (movieReview.tittle.isNotEmpty() && movieReview.description.isNotEmpty() && movieReview.reviewer.isNotEmpty()) {
                movieReviews.add(movieReview.copy())
//                i("add Button Pressed: ${movieReview.tittle} + ${movieReview.description} + ${movieReview.reviewer}")
                i("add Button Pressed: $movieReview")
                for (i in movieReviews.indices)
                { i("Movireview[$i]:${this.movieReviews[i]}")}
            }
            else {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}