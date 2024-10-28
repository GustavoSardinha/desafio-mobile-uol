package com.sardinha.desafiouol.models

import android.os.Parcel
import android.os.Parcelable

data class PremiereDate(
    val localDate: String,
    val dayOfWeek: String,
    val dayAndMonth: String,
    val year: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(localDate)
        parcel.writeString(dayOfWeek)
        parcel.writeString(dayAndMonth)
        parcel.writeString(year)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PremiereDate> {
        override fun createFromParcel(parcel: Parcel): PremiereDate {
            return PremiereDate(parcel)
        }

        override fun newArray(size: Int): Array<PremiereDate?> {
            return arrayOfNulls(size)
        }
    }
}
