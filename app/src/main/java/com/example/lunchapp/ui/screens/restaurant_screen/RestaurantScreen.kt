package com.example.lunchapp.ui.screens.restaurant_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lunchapp.domain.data.RestaurantMenuMock
import com.example.lunchapp.ui.screens.mainscreen.components.TopBar
import com.example.lunchapp.ui.screens.restaurant_screen.components.MenuCategoryItem
import com.example.lunchapp.ui.screens.restaurant_screen.components.RestaurantTopBar
import com.example.lunchapp.ui.theme.AppColors

@Composable
fun RestaurantScreen(modifier: Modifier = Modifier) {
    Surface(color = AppColors.background) {
        Column(modifier = modifier) {
            RestaurantTopBar(
                onBackClick = {},
                onProfileClick = {},
                title = "Gusto Ristorante"
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                itemsIndexed(RestaurantMenuMock.data) { index, item ->
                    val itModifier = if (index == 0) Modifier else
                        Modifier.padding(top = 16.dp)
                    MenuCategoryItem(
                        modifier = itModifier,
                        item = item
                    )
                }
//            items(RestaurantMenuMock.data) { categoryItem ->
//                MenuCategoryItem(categoryItem)
//            }
            }
        }
    }
}

