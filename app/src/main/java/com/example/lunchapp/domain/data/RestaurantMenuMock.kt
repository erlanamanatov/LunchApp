package com.example.lunchapp.domain.data

import com.example.lunchapp.R
import com.example.lunchapp.model.Food
import com.example.lunchapp.model.IngredientTag

object RestaurantMenuMock {
    val data = listOf<MenuCategory>(
        MenuCategory(
            name = "Antipasti",
            foods = listOf(
                Food(
                    name = "Bruschetta Basilica",
                    price = 3.50f,
                    img = randomFoodImg(),
                    ingredients = ingredients,
                    info = randomInfoTag(),
                    ingredientTag = vegTag
                ),
                Food(
                    name = "Deep fried camembert",
                    price = 5.25f,
                    img = randomFoodImg(),
                    ingredients = ingredients,
                    info = randomInfoTag(),
                    ingredientTag = vegTag
                ),
                Food(
                    name = "Calamari fritti",
                    price = 6.00f,
                    img = randomFoodImg(),
                    ingredients = ingredients,
                    info = randomInfoTag(),
                    ingredientTag = fishTag
                ),
            )
        ),
        MenuCategory(
            name = "Soups",
            foods = listOf(
                Food(
                    name = "Seasonal soup",
                    price = 4.49f,
                    img = R.drawable.food_soup1,
                    info = "300ml",
                    ingredients = ingredients,
                    ingredientTag = fishTag
                ),
                Food(
                    name = "Tomato soup with pesto",
                    price = 6.25f,
                    img = R.drawable.food_soup2,
                    info = "350ml",
                    ingredients = ingredients,
                    ingredientTag = vegTag
                )
            )
        ),
        MenuCategory(
            name = "Pizza and Burgers",
            foods = listOf(
                Food(
                    name = "Margheritta",
                    price = 8.50f,
                    img = R.drawable.food_pizza1,
                    info = "32 cm",
                    ingredients = ingredients,
                    ingredientTag = vegTag
                ),
                Food(
                    name = "Siciliana",
                    price = 10.30f,
                    img = R.drawable.food_pizza2,
                    info = "40cm",
                    ingredients = ingredients,
                    ingredientTag = meatTag
                ),
                Food(
                    name = "Classic Burger",
                    price = 5.20f,
                    img = R.drawable.food_burger1,
                    info = "250g",
                    ingredients = ingredients,
                    ingredientTag = meatTag
                ),
                Food(
                    name = "Cowboy Burger",
                    price = 7.15f,
                    img = R.drawable.food_burger2,
                    info = "400g",
                    ingredients = ingredients,
                    ingredientTag = meatTag
                ),
                Food(
                    name = "Quattro Formaggi",
                    price = 11.80f,
                    img = R.drawable.food_pizza1,
                    info = "35 cm",
                    ingredients = ingredients,
                    ingredientTag = fishTag
                ),
                Food(
                    name = "Borino",
                    price = 9.90f,
                    img = R.drawable.food_pizza2,
                    info = "32cm",
                    ingredients = ingredients,
                    ingredientTag = vegTag
                )
            )
        )
    )
}

data class MenuCategory(
    val name: String,
    val foods: List<Food>
)

 val ingredients = listOf(
    "tomato sauce",
    "fresh mozzarella cheese",
    "fresh basil leaf",
    "ricotta cheese",
    "eggs",
    "Bacon"
)


 fun randomFoodImg(): Int {
    return listOf(
        R.drawable.food1,
        R.drawable.food2,
        R.drawable.food3,
        R.drawable.food4,
        R.drawable.food5
    )
        .shuffled()
        .first()
}

 val fishTag = IngredientTag("fish", R.drawable.img_fish)
 val meatTag = IngredientTag("fish", R.drawable.img_meat)
 val vegTag = IngredientTag("fish", R.drawable.img_veg)


 fun randomInfoTag(): String {
    return listOf(
        "2 pcs", "150g", "250g", "330g"
    ).shuffled().first()
}

