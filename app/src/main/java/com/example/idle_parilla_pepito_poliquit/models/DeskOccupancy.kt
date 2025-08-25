package com.example.idle_parilla_pepito_poliquit.models

data class DeskOccupancy(
    val id: String = "",
    val deskId: String = "",
    val status: String = "", // "Occupied" or "Idle"
    val timestamp: String = "",
    val duration: Long = 0L, // in minutes
    val userId: String = ""
)