package ie.setu.moviereview_app.main

import android.app.Application
import ie.setu.moviereview_app.models.MoviereviewModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val movieReview  = ArrayList<MoviereviewModel>()
    override fun onCreate() {

        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("MovieReviewApp Started")
        //movieReview.add(MoviereviewModel("hello","bye","there"))

    }

}