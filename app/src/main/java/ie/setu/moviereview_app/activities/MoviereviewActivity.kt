package ie.setu.moviereview_app.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
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
    var edit = false

    //var movieReviews = ArrayList<MoviereviewModel>()
    lateinit var app : MainApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        var edit = true
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
            binding.deleteData.setText(R.string.delete_movieData)
            Picasso.get()
                .load(movieReview.image)
                .into(binding.placemarkImage)
            if (movieReview.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_movie_image)
            }

        }
        //setContentView(R.layout.activity_moviereview)

        //Timber.plant(Timber.DebugTree())
        i("Moviereview Activity started.. ")

        binding.btnAdd.setOnClickListener() {

            movieReview.tittle = binding.moviereviewTitle.text.toString()
            movieReview.description = binding.movieReviewDescription.text.toString()
            movieReview.reviewer = binding.movieReviewReviewname.text.toString()
            if (movieReview.tittle.isEmpty() && movieReview.description.isEmpty() && movieReview.reviewer.isEmpty()) {
                Snackbar.make(it,R.string.enter_Moviereview_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.movieReviewss.update(movieReview.copy())
                } else {
                    app.movieReviewss.create(movieReview.copy())
                }
            }
            i("add Button Pressed: $movieReview")
            setResult(RESULT_OK)
            finish()
            }

        registerImagePickerCallback()
//        binding.chooseImage.setOnClickListener {
//            i("Select image")
//        }

        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher,this)
        }
//        binding.deleteData.setOnClickListener {
//            if (edit) {
//                i("Delete button clicked")
//                showDeleteConfirmationDialog()
//            }
//        }




    }

//    private fun showDeleteConfirmationDialog() {
//        AlertDialog.Builder(this)
//            .setTitle(R.string.delete_movieData)
//            .setMessage(R.string.delete_movieData)
//            .setPositiveButton("Yes") { _, _ ->
//                i("User confirmed delete. Calling deleteMovieReview.")
//                deleteMovieReview()
//            }
//            .setNegativeButton("No", null)
//            .show()
//    }


//    private fun deleteMovieReview() {
//
//        Timber.i("Deleting movie review with ID: ${movieReview.id}")
//        Timber.d("Movie review IDs in the list: ${app.movieReviews.map { it.id }}")
//
//        val removed = app.movieReviews.remove(movieReview)
//        if (removed) {
//            i("Movie review deleted successfully. Remaining reviews: ${app.movieReviews.size}")
//        } else {
//            Timber.e("Failed to delete movie review.")
//        }
//
//        setResult(RESULT_OK)
//        finish()
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_moviereview, menu)
        if (edit) menu.getItem(0).isVisible = true
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.item_delete -> {
                setResult(99)
                app.movieReviewss.delete(movieReview)
//                setResult(RESULT_OK)
                finish()
            }
            R.id.item_cancel -> {
                finish();
            }
//            R.id.item_delete -> {
//                if (edit) {
//                    app.deleteMovieReview(movieReview);
//                    setResult(RESULT_OK);
//                    finish();
//                }
//            }
        }
        return super.onOptionsItemSelected(item);
    }

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            val image = result.data!!.data!!
                            contentResolver.takePersistableUriPermission(image,
                                Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            movieReview.image =image
                            //movieReview.image = result.data!!.data!!
                            Picasso.get()
                                .load(movieReview.image)
                                .into(binding.placemarkImage)
                            binding.chooseImage.setText(R.string.change_movie_image)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }
}