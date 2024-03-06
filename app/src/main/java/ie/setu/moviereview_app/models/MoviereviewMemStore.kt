package ie.setu.moviereview_app.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class MoviereviewMemStore : MoviereviewStore{

    val movieReview = ArrayList<MoviereviewModel>()


    override fun findAll(): List<MoviereviewModel> {
        return movieReview
    }

    override fun create(movieReviews: MoviereviewModel) {
        movieReviews.id = getId()
        movieReview.add(movieReviews)
        logAll()
    }

    override fun update(moviereview: MoviereviewModel) {
        var updateMovie: MoviereviewModel? = movieReview.find { p -> p.id == moviereview.id }
        if (updateMovie != null) {
            updateMovie.tittle = moviereview.tittle
            updateMovie.description = moviereview.description
            updateMovie.reviewer = moviereview.reviewer
            logAll()
        }
    }


    private fun logAll() {
        movieReview.forEach { i("$it") }
    }
}