package ie.setu.moviereview_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import ie.setu.moviereview_app.R
import ie.setu.moviereview_app.databinding.ActivityMoviereviewBinding
import ie.setu.moviereview_app.main.MainApp
import ie.setu.moviereview_app.models.MoviereviewModel
import timber.log.Timber
import timber.log.Timber.i


class MoviereviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviereviewBinding

    var movieReview = MoviereviewModel()
    //var movieReviews = ArrayList<MoviereviewModel>()
    lateinit var app : MainApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviereviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)
        app = application as MainApp
        //setContentView(R.layout.activity_moviereview)

        //Timber.plant(Timber.DebugTree())
        i("Moviereview Activity started.. ")

        binding.btnAdd.setOnClickListener() {

            movieReview.tittle = binding.moviereviewTitle.text.toString()
            movieReview.description = binding.movieReviewDescription.text.toString()
            movieReview.reviewer = binding.movieReviewReviewname.text.toString()
            if (movieReview.tittle.isNotEmpty() && movieReview.description.isNotEmpty() && movieReview.reviewer.isNotEmpty()) {
               app.movieReview.add(movieReview.copy())
//                i("add Button Pressed: ${movieReview.tittle} + ${movieReview.description} + ${movieReview.reviewer}")
                i("add Button Pressed: $movieReview")
                for (i in app.movieReview.indices)
                { i("Movie reviews[$i]:${this.app.movieReview[i]}")}

                setResult(RESULT_OK)
                finish()
            }
            else {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }

//            override fun onCreateOptionsMenu(menu: Menu): Boolean {
//                menuInflater.inflate(R.menu.menu_moviereview, menu)
//                return super.onCreateOptionsMenu(menu)
//            }
        }

//        override fun onCreateOptionsMenu(menu: Menu): Boolean {
//            menuInflater.inflate(R.menu.menu_moviereview, menu)
//            return super.onCreateOptionsMenu(menu)
//        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_moviereview, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> { finish() }
        }
        return super.onOptionsItemSelected(item)
    }
}