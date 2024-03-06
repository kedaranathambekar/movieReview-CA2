package ie.setu.moviereview_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import ie.setu.moviereview_app.R
import ie.setu.moviereview_app.databinding.ActivityMoviereviewBinding
import ie.setu.moviereview_app.helpers.showImagePicker
import ie.setu.moviereview_app.main.MainApp
import ie.setu.moviereview_app.models.MoviereviewModel
import timber.log.Timber
import timber.log.Timber.i


class MoviereviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviereviewBinding

    var movieReview = MoviereviewModel()
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>

    //var movieReviews = ArrayList<MoviereviewModel>()
    lateinit var app : MainApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var edit = false
        binding = ActivityMoviereviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)
        app = application as MainApp

        if (intent.hasExtra("placemark_edit")) {
            edit = true

            movieReview = intent.extras?.getParcelable("placemark_edit")!!
            binding.moviereviewTitle.setText(movieReview.tittle)
            binding.movieReviewDescription.setText(movieReview.description)
            binding.movieReviewReviewname.setText(movieReview.reviewer)
            binding.btnAdd.setText(R.string.save_moviereview)
        }
        //setContentView(R.layout.activity_moviereview)

        //Timber.plant(Timber.DebugTree())
        i("Moviereview Activity started.. ")

        binding.btnAdd.setOnClickListener() {

            movieReview.tittle = binding.moviereviewTitle.text.toString()
            movieReview.description = binding.movieReviewDescription.text.toString()
            movieReview.reviewer = binding.movieReviewReviewname.text.toString()
            if (movieReview.tittle.isEmpty() && movieReview.description.isEmpty() && movieReview.reviewer.isEmpty()) {
               //app.movieReviews.create(movieReview.copy())
//                i("add Button Pressed: ${movieReview.tittle} + ${movieReview.description} + ${movieReview.reviewer}")
//                i("add Button Pressed: $movieReview")
//                for (i in app.movieReview.indices)
//                { i("Movie reviews[$i]:${this.app.movieReview[i]}")}

                Snackbar.make(it,R.string.enter_Moviereview_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.movieReviews.update(movieReview.copy())
                } else {
                    app.movieReviews.create(movieReview.copy())
                }
            }
            i("add Button Pressed: $movieReview")
            setResult(RESULT_OK)
            finish()
            }

//        binding.chooseImage.setOnClickListener {
//            i("Select image")
//        }

        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher)
        }


//            else {
//                Snackbar
//                    .make(it,R.string.enter_Moviereview_title, Snackbar.LENGTH_LONG)
//                    .show()
//            }

//            override fun onCreateOptionsMenu(menu: Menu): Boolean {
//                menuInflater.inflate(R.menu.menu_moviereview, menu)
//                return super.onCreateOptionsMenu(menu)
//            }


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

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }
}