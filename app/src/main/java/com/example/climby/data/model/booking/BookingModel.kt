package com.example.climby.data.model.booking

import android.os.Parcel
import android.os.Parcelable
import com.example.climby.data.model.user.UserModel
import com.google.gson.annotations.SerializedName

data class BookingModel(
        @SerializedName("id") val id: Int,
        @SerializedName("userId") val passenger: UserModel?,
        @SerializedName("idTravel") val tripId: Int,
        @SerializedName("reservationStatus") val status: Boolean?,
        @SerializedName("valuationStatus") var valuationStatus: Boolean?,
        @SerializedName("date") val date: String?,
        ): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readParcelable(UserModel::class.java.classLoader),
                parcel.readInt(),
                parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
                parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
                parcel.readString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(id)
                parcel.writeParcelable(passenger, flags)
                parcel.writeInt(tripId)
                parcel.writeValue(status)
                parcel.writeValue(valuationStatus)
                parcel.writeString(date)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<BookingModel> {
                override fun createFromParcel(parcel: Parcel): BookingModel {
                        return BookingModel(parcel)
                }

                override fun newArray(size: Int): Array<BookingModel?> {
                        return arrayOfNulls(size)
                }
        }
}

