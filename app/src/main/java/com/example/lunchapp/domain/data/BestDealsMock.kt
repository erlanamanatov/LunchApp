package com.example.lunchapp.domain.data

import com.example.lunchapp.R
import com.example.lunchapp.model.Food

object BestDealsMock {
    val data = listOf<Food>(
        Food(
            name = "Deep fried camembert",
            price = 5.25f,
            img = randomFoodImg(),
            ingredients = ingredients,
            info = randomInfoTag(),
            ingredientTag = vegTag,
            restaurant = randomRestName()
        ),
        Food(
            name = "Tomato soup with pesto",
            price = 6.25f,
            img = R.drawable.food_soup2,
            info = "350ml",
            ingredients = ingredients,
            restaurant = randomRestName(),
            ingredientTag = vegTag
        ),
        Food(
            name = "Margheritta",
            price = 8.50f,
            img = R.drawable.food_pizza1,
            info = "32 cm",
            ingredients = ingredients,
            ingredientTag = vegTag,
            restaurant = randomRestName(),
        ),
        Food(
            name = "Classic Burger",
            price = 5.20f,
            img = R.drawable.food_burger1,
            info = "250g",
            ingredients = ingredients,
            ingredientTag = meatTag,
            restaurant = randomRestName()
        )
    )
}

private fun randomRestName(): String {
    return listOf<String>(
        "Le Bernardin",
        "The French Laundry",
        "Belcanto",
        "Alinea",
        "Piazza Duomo",
        "Odette"
    )
        .shuffled()
        .first()
}