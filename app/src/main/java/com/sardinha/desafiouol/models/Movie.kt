package com.sardinha.desafiouol.models

import android.os.Parcel
import android.os.Parcelable

data class Movie(
    val title: String,
    val synopsis: String?,
    val inPreSale: Boolean?,
    val images: List<Image>?,
    val premiereDate: PremiereDate?,
    val director: String?,
    val contentRating: String?,
    val duration: String?,
    val genres: List<String>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.createTypedArrayList(Image.CREATOR),
        parcel.readParcelable(PremiereDate::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(synopsis)
        parcel.writeValue(inPreSale)
        parcel.writeTypedList(images)
        parcel.writeParcelable(premiereDate, flags)
        parcel.writeString(director)
        parcel.writeString(contentRating)
        parcel.writeString(duration)
        parcel.writeStringList(genres)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}
