package com.example.lunchapp.ui.screens.restaurant_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lunchapp.domain.data.RestaurantMenuMock
import com.example.lunchapp.model.Food
import com.example.lunchapp.ui.theme.AppColors
import com.example.lunchapp.ui.theme.LunchAppTheme

@ExperimentalMaterialApi
@Composable
fun FoodMenuItem(
    modifier: Modifier = Modifier,
    food: Food,
    onClick: (Food) -> Unit
) {
    Card(
        modifier = modifier,
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
        onClick = { onClick(food) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FoodInfo(
                modifier = Modifier.weight(1f),
                food = food
            )
            Text(
                text = "${food.price} €",
                fontSize = 16.sp,
                color = Color(0xFFacacac)
            )
            Spacer(Modifier.width(16.dp))
            Icon(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = Color(0xFFe2e2e2),
                        shape = CircleShape
                    )
                    .padding(4.dp),
                imageVector = Icons.Default.Add, contentDescription = "",
                tint = MaterialTheme.colors.secondary
            )
            Spacer(Modifier.width(4.dp))
        }
    }

}

@Composable
fun FoodInfo(
    modifier: Modifier = Modifier,
    food: Food
) {
    Column(modifier = modifier) {
        Text(
            text = food.name,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        FoodTags(food = food)
    }
}

@Composable
fun FoodTags(
    modifier: Modifier = Modifier,
    food: Food
) {
    Row(
        modifier = modifier.padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(4.dp),
                )
                .background(color = Color(0xffe7e7e7)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = food.info, color = Color.DarkGray,
                fontSize = 10.sp
            )
        }
        Spacer(Modifier.width(8.dp))
        food.ingredientTag?.let {
            Image(
                painter = painterResource(id = it.img), contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
        }
    }

}


@ExperimentalMaterialApi
@Preview
@Composable
fun FoodMenuItemPreview() {
    LunchAppTheme {
        androidx.compose.material.Surface(color = AppColors.background) {
            FoodMenuItem(
                modifier = Modifier.padding(24.dp),
                food = RestaurantMenuMock.data.first().foods.first(),
                onClick = {}
            )
        }
    }
}