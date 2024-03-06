package ie.setu.moviereview_app.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviereviewModel(var id: Long = 0,var tittle:String = "", var description:String= "", var reviewer:String = "",   var image: Uri = Uri.EMPTY
) :
    Parcelable
