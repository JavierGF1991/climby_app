package com.app.climby.data.network.booking

import com.app.climby.data.model.booking.BookingModel
import com.app.climby.data.model.user.UserModel
import retrofit2.Response
import retrofit2.http.*

interface BookingApiClient {
    @PUT("reservation")
    suspend fun putBooking(@Body bookingModel: BookingModel?): Response<BookingModel>

    @POST("reservation")
    suspend fun postBooking(@Body bookingModel: BookingModel?): Response<BookingModel>

    @DELETE("reservation/{id}")
    suspend fun deleteBooking(@Path("id") id: Int)

    @GET("reservation/user/{id}")
    suspend fun getNotificationByUserId(@Path("id") id: Int): Response<Int>
}