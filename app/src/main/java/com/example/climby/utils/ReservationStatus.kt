package com.example.climby.utils

enum class ReservationStatus(val status: Boolean?) {
    REFUSE(null),
    ACCEPTED(true),
    UNANSWERED(false),
}