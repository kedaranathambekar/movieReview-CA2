package ie.setu.moviereview_app.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class MoviereviewMemStore : MoviereviewStore{

    val movieReviews = ArrayList<MoviereviewModel>()


    override fun findAll(): List<MoviereviewModel> {
        return movieReviews
    }

    override fun create(movieReview: MoviereviewModel) {
        movieReview.id = getId()
        this.movieReviews.add(movieReview)
        logAll()
    }

    override fun update(movieReview: MoviereviewModel) {
        var updateMovie: MoviereviewModel? = this.movieReviews.find { p -> p.id == movieReview.id }
        if (updateMovie != null) {
            updateMovie.tittle = movieReview.tittle
            updateMovie.description = movieReview.description
            updateMovie.reviewer = movieReview.reviewer
            updateMovie.image = movieReview.image
            logAll()
        }
    }

    override fun delete(movieReview: MoviereviewModel) {
        movieReviews.remove(movieReview)
    }

    override fun findOne(id: Long): MoviereviewModel? {
        var foundPlacemark: MoviereviewModel? = movieReviews.find { p -> p.id == id }
        return foundPlacemark
    }


    private fun logAll() {
        movieReviews.forEach { i("$it") }
    }
}