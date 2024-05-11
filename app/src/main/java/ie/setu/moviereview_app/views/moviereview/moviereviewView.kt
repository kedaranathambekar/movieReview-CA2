package ie.setu.moviereview_app.views.moviereview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import ie.setu.moviereview_app.R
import ie.setu.moviereview_app.databinding.ActivityMoviereviewBinding
import ie.setu.moviereview_app.helpers.showImagePicker
import ie.setu.moviereview_app.main.MainApp
import ie.setu.moviereview_app.models.MoviereviewModel
import timber.log.Timber
import timber.log.Timber.i

class moviereviewView : AppCompatActivity() {

    private lateinit var binding: ActivityMoviereviewBinding
    private lateinit var presenter: MoviereviewPresenter
    var moviereview = MoviereviewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMoviereviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        presenter = MoviereviewPresenter(this)

        binding.chooseImage.setOnClickListener {
            presenter.cacheMovie(binding.moviereviewTitle.text.toString(), binding.movieReviewDescription.text.toString(), binding.movieReviewReviewname.text.toString())
            presenter.doSelectImage()
        }



        binding.btnAdd.setOnClickListener {
            if (binding.moviereviewTitle.text.toString().isEmpty()) {
                Snackbar.make(binding.root, R.string.enter_Moviereview_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                // presenter.cachePlacemark(binding.placemarkTitle.text.toString(), binding.description.text.toString())
                presenter.doAddOrSave(binding.moviereviewTitle.text.toString(), binding.movieReviewDescription.text.toString(), binding.movieReviewReviewname.text.toString())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_moviereview, menu)
        val deleteMenu: MenuItem = menu.findItem(R.id.item_delete)
        deleteMenu.isVisible = presenter.edit
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_delete -> {
                presenter.doDelete()
            }
            R.id.item_cancel -> {
                presenter.doCancel()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun showMovies(moviereviwess: MoviereviewModel) {
        binding.moviereviewTitle.setText(moviereviwess.tittle)
        binding.movieReviewDescription.setText(moviereviwess.description)
        binding.movieReviewReviewname.setText(moviereviwess.reviewer)
        binding.btnAdd.setText(R.string.save_moviereview)
        Picasso.get()
            .load(moviereviwess.image)
            .into(binding.placemarkImage)
        if (moviereviwess.image != Uri.EMPTY) {
            binding.chooseImage.setText(R.string.change_movie_image)
        }
    }

    fun updateImage(image: Uri) {
        i("Image updated")
        Picasso.get()
            .load(image)
            .into(binding.placemarkImage)
        binding.chooseImage.setText(R.string.change_movie_image)
    }
}