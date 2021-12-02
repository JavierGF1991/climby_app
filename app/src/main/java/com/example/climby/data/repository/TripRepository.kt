package com.example.climby.data.repository

import com.example.climby.data.network.trip.TripService
import javax.inject.Inject

class TripRepository @Inject constructor(private val api : TripService){
    suspend fun getTrips() = api.getTrips()
    suspend fun getTripsUser(id: Int) = api.getTripsUser(id)
    suspend fun getTravelsWithUserReservation(id: Int) = api.getTravelsWithUserReservation(id)
}