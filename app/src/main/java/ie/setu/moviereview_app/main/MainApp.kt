package ie.setu.moviereview_app.main

import MoviereviewJSONStore
import android.app.Application
import ie.setu.moviereview_app.models.MoviereviewMemStore
import ie.setu.moviereview_app.models.MoviereviewModel
import ie.setu.moviereview_app.models.MoviereviewStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {


    lateinit var movieReviewss: MoviereviewStore

    override fun onCreate() {

        super.onCreate()
        Timber.plant(Timber.DebugTree())
        movieReviewss = MoviereviewJSONStore(applicationContext)

        i("MovieReviewApp Started")


    }



}