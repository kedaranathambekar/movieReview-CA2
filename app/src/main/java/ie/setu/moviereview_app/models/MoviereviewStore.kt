package ie.setu.moviereview_app.models

interface MoviereviewStore {

    fun findAll(): List<MoviereviewModel>
    fun create(movieReview: MoviereviewModel)

    fun update(placemark: MoviereviewModel)
}