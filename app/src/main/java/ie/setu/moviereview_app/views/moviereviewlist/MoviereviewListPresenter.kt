package ie.setu.moviereview_app.views.moviereviewlist

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import ie.setu.moviereview_app.main.MainApp
import ie.setu.moviereview_app.models.MoviereviewModel
import ie.setu.moviereview_app.views.moviereview.moviereviewView

class MoviereviewListPresenter(val view: MoviereviewListView) {
    var app: MainApp
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>

    private var position: Int = 0

    init {
        app = view.application as MainApp

        registerRefreshCallback()
    }

    fun getPlacemarks() = app.movieReviewss.findAll()

    fun doAddPlacemark() {
        val launcherIntent = Intent(view, moviereviewView::class.java)
        refreshIntentLauncher.launch(launcherIntent)
    }

    fun doEditPlacemark(placemark: MoviereviewModel, pos: Int) {
        val launcherIntent = Intent(view, moviereviewView::class.java)
        launcherIntent.putExtra("placemark_edit", placemark)
        position = pos
        refreshIntentLauncher.launch(launcherIntent)
    }

//    fun doShowPlacemarksMap() {
//        val launcherIntent = Intent(view, PlacemarkMapsActivity::class.java)
//        mapIntentLauncher.launch(launcherIntent)
//    }

    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            view.registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) {
                if (it.resultCode == Activity.RESULT_OK) view.onRefresh()
                else // Deleting
                    if (it.resultCode == 99) view.onDelete(position)
            }
    }
//    private fun registerMapCallback() {
//        mapIntentLauncher = view.registerForActivityResult(ActivityResultContracts.StartActivityForResult())
//        {  }
//    }
}