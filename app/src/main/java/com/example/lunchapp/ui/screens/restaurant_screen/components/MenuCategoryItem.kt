package com.example.lunchapp.ui.screens.restaurant_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lunchapp.domain.data.MenuCategory
import com.example.lunchapp.domain.data.RestaurantMenuMock
import com.example.lunchapp.model.Food
import com.example.lunchapp.ui.theme.LunchAppTheme

@ExperimentalMaterialApi
@Composable
fun MenuCategoryItem(
    item: MenuCategory, modifier: Modifier = Modifier,
    onItemClick: (Food) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = item.name,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(Modifier.height(12.dp))
        for (food in item.foods) {
            FoodMenuItem(
                modifier = Modifier.fillMaxWidth(),
                food = food,
                onClick = onItemClick
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun MenuCategoryItemPreview() {
    LunchAppTheme {
        androidx.compose.material.Surface(color = MaterialTheme.colors.background) {
            MenuCategoryItem(
                item = RestaurantMenuMock.data.first(),
                modifier = Modifier.padding(24.dp),
                onItemClick = {}
            )
        }
    }
}