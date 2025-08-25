package com.example.idle_parilla_pepito_poliquit.models

data class DeskInfo(
    val id: String = "",
    val name: String = "",
    val location: String = "",
    val currentStatus: String = "Idle",
    val lastUpdated: String = "",
    val todayUsage: Long = 0L // in minutes
)