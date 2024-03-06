package ie.setu.moviereview_app.adapters

import ie.setu.moviereview_app.models.MoviereviewModel

interface MoviereviewListener {
    fun onMoviereviewClick(movieReview: MoviereviewModel)
}