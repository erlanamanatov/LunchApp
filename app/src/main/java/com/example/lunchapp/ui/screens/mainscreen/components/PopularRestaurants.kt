package com.example.lunchapp.ui.screens.mainscreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lunchapp.domain.data.RestaurantsMock
import com.example.lunchapp.model.Restaurant
import com.example.lunchapp.ui.theme.LunchAppTheme
import java.util.*

@ExperimentalMaterialApi
@Composable
fun PopularRestaurants(
    modifier: Modifier,
    restaurants: List<Restaurant>,
    onRestaurantClick: (Restaurant) -> Unit = {}
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Header()
        Spacer(Modifier.height(16.dp))
        for (item in restaurants) {
            RestaurantItem(
                item = item,
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onRestaurantClick(it)
                }
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun Header() {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = "Popular restaurants",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Text(
            text = "show all".uppercase(Locale.getDefault()),
            color = MaterialTheme.colors.secondary,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
        )
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun PopularRestaurantsPreview() {
    LunchAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            PopularRestaurants(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                restaurants = RestaurantsMock.data
            )
        }
    }
}
