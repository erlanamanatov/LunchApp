package com.example.lunchapp.model

data class Food(
    val name: String,
    val price: Float,
    val restaurant: String? = null,
    val img: Int,
    val info: String,
    val ingredients: List<String> = emptyList(),
    val ingredientTag: IngredientTag? = null
)