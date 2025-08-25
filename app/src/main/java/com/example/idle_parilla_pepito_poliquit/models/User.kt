package com.example.idle_parilla_pepito_poliquit.models

data class User(
    val id: String = "",
    val email: String = "",
    val name: String = "",
    val department: String = "",
    val createdAt: Long = System.currentTimeMillis()
)