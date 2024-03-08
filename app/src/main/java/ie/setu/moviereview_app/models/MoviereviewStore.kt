package ie.setu.moviereview_app.models

interface MoviereviewStore {

    fun findAll(): List<MoviereviewModel>
    fun create(movieReview: MoviereviewModel)

    fun update(movieReview: MoviereviewModel)

    fun findOne(id : Long) : MoviereviewModel?

    fun delete (movieReview: MoviereviewModel)


}