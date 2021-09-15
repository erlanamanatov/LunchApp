package com.example.lunchapp.domain.data

import com.example.lunchapp.R
import com.example.lunchapp.model.Restaurant

object RestaurantsMock {
    val data = listOf<Restaurant>(
        Restaurant(
            name = "Limoncello",
            workingHours = "12.30 - 13.00",
            tags = listOf("lunch sets", "sandwiches"),
            logo = R.drawable.resto1
        ),
        Restaurant(
            name = "Gusto Ristorante",
            workingHours = "13.00 - 13.30",
            tags = listOf("italian", "pasta", "pizza"),
            logo = R.drawable.resto2
        ),
        Restaurant(
            name = "Pino Cono",
            workingHours = "12.30 - 13.30",
            tags = listOf("lunch sets", "burgers"),
            logo = R.drawable.resto3
        ),
    )
}