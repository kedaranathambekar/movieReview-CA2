package ie.setu.moviereview_app.views.moviereview

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import ie.setu.moviereview_app.databinding.ActivityMoviereviewBinding
import ie.setu.moviereview_app.helpers.showImagePicker
import ie.setu.moviereview_app.main.MainApp
import ie.setu.moviereview_app.models.MoviereviewModel

import timber.log.Timber
class MoviereviewPresenter(private val view: moviereviewView) {

    var moviereview = MoviereviewModel()
    var app: MainApp = view.application as MainApp
    var binding: ActivityMoviereviewBinding = ActivityMoviereviewBinding.inflate(view.layoutInflater)
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>

    var edit = false;

    init {
        if (view.intent.hasExtra("movireview_edit")) {
            edit = true
            moviereview = view.intent.extras?.getParcelable("moviereview_edit")!!
            view.showMovies(moviereview)
        }
        registerImagePickerCallback()

    }
    fun doAddOrSave(title: String, description: String, reviewer: String) {
        moviereview.tittle = title
        moviereview.description = description
        moviereview.description = reviewer
        if (edit) {
            app.movieReviewss.update(moviereview)
        } else {
            app.movieReviewss.create(moviereview)
        }
        view.setResult(RESULT_OK)
        view.finish()
    }
    fun doCancel() {
        view.finish()
    }
    fun doDelete() {
        view.setResult(99)
        app.movieReviewss.delete(moviereview)
        view.finish()
    }
    fun doSelectImage() {
        showImagePicker(imageIntentLauncher,view)
    }

    fun cacheMovie (title: String, description: String,reviewer: String) {
        moviereview.tittle = title;
        moviereview.description = description;
        moviereview.reviewer = reviewer
    }

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            view.registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    AppCompatActivity.RESULT_OK -> {
                        if (result.data != null) {
                            Timber.i("Got Result ${result.data!!.data}")
                            moviereview.image = result.data!!.data!!
                            view.contentResolver.takePersistableUriPermission(moviereview.image,
                                Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            view.updateImage(moviereview.image)
                        } // end of if
                    }
                    AppCompatActivity.RESULT_CANCELED -> { } else -> { }
                }            }    }


}