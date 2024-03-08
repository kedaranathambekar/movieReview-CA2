
import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import ie.setu.moviereview_app.helpers.*
import ie.setu.moviereview_app.models.MoviereviewModel
import ie.setu.moviereview_app.models.MoviereviewStore
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "placemarks.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<MoviereviewModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class MoviereviewJSONStore(private val context: Context) : MoviereviewStore {

    var movieReviews = mutableListOf<MoviereviewModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<MoviereviewModel> {
        logAll()
        return movieReviews
    }

    override fun create(movieReview: MoviereviewModel) {
        movieReview.id = generateRandomId()
        movieReviews.add(movieReview)
        serialize()
    }

    override fun findOne(id: Long) : MoviereviewModel? {
        var foundPlacemark: MoviereviewModel? = movieReviews.find { p -> p.id == id }
        return foundPlacemark
    }


    override fun update(movieReview: MoviereviewModel) {
        val movieList = findAll() as ArrayList<MoviereviewModel>
        var foundMovie: MoviereviewModel? = movieList.find { p -> p.id == movieReview.id }
        if (foundMovie != null) {
            foundMovie.tittle = movieReview.tittle
            foundMovie.description = movieReview.description
            foundMovie.reviewer = movieReview.reviewer
            foundMovie.image = movieReview.image

        }
        serialize()
    }

    override fun delete(movieReview: MoviereviewModel) {
        movieReviews.remove(movieReview)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(movieReviews, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        movieReviews = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        movieReviews.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}
