package com.example.lunchapp.model

data class Food(
    val name: String,
    val price: Float,
    val img: Int? = null,
    val info: String,
    val ingredients: List<String> = emptyList(),
    val ingredientTag: IngredientTag? = null
)