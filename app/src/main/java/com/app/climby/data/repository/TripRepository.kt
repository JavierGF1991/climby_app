package com.app.climby.data.repository

import com.app.climby.data.model.trip.TripModel
import com.app.climby.data.network.trip.TripService
import javax.inject.Inject

class TripRepository @Inject constructor(private val api : TripService){
    suspend fun getTrips() = api.getTrips()
    suspend fun getTripsUser(id: Int) = api.getTripsUser(id)
    suspend fun getTripById(id: Int) = api.getTripById(id)
    suspend fun getTravelsWithUserReservation(id: Int) = api.getTravelsWithUserReservation(id)
    suspend fun postTrip(tripModel: TripModel) = api.postTrip(tripModel)
    suspend fun putTrip(tripModel: TripModel) = api.putTrip(tripModel)
    suspend fun deleteTrip(id: Int) = api.deleteTrip(id)
}