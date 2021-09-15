package com.example.lunchapp.model

data class Restaurant(
    val name: String,
    val workingHours: String,
    val tags: List<String> = emptyList(),
    val logo: Int
)