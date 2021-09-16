package com.example.lunchapp.ui.screens.mainscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lunchapp.domain.data.BestDealsMock
import com.example.lunchapp.domain.data.RestaurantsMock
import com.example.lunchapp.ui.screens.mainscreen.components.BestDeals
import com.example.lunchapp.ui.screens.mainscreen.components.PopularRestaurants
import com.example.lunchapp.ui.screens.mainscreen.components.SearchComponent
import com.example.lunchapp.ui.screens.mainscreen.components.TopBar
import com.example.lunchapp.ui.theme.AppColors

@ExperimentalMaterialApi
@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    var textToSearch by remember { mutableStateOf("") }

    Surface(modifier = modifier, color = AppColors.background) {
        Column {
            TopBar()
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                text = "What for lunch today?",
                style = TextStyle(
                    fontSize = 32.sp, fontWeight = FontWeight.Bold,
                )
            )
            SearchComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                value = textToSearch,
                onValueChange = { textToSearch = it }
            )
            Spacer(Modifier.height(24.dp))
            BestDeals(
                modifier = Modifier
                    .fillMaxWidth(),
                foods = BestDealsMock.data
            )
            PopularRestaurants(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                restaurants = RestaurantsMock.data
            )
        }
    }
}

