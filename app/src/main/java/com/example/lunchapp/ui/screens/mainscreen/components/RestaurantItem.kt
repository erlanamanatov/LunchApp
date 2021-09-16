package com.example.lunchapp.ui.screens.mainscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lunchapp.R
import com.example.lunchapp.domain.data.RestaurantsMock
import com.example.lunchapp.model.Restaurant
import com.example.lunchapp.ui.theme.LunchAppTheme

@Composable
fun RestaurantItem(
    modifier: Modifier = Modifier,
    item: Restaurant
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(id = item.logo),
                modifier = Modifier
                    .size(80.dp, 60.dp),
//                    .border(1.dp, color = Color.Black),
                contentScale = ContentScale.Inside,
                contentDescription = ""
            )
            RestaurantInfo(
                modifier = Modifier.weight(1f),
                restaurant = item
            )
        }
    }
}


@Composable
private fun RestaurantInfo(
    modifier: Modifier = Modifier,
    restaurant: Restaurant
) {
    Column(modifier = modifier.padding(start = 8.dp)) {
        Text(
            text = restaurant.name,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.Schedule,
                modifier = Modifier.size(24.dp),
                tint = Color.DarkGray,
                contentDescription = ""
            )
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = restaurant.workingHours,
                color = Color.DarkGray,
                fontSize = 12.sp
            )
        }
        val tags = restaurant.tags.joinToString(
            separator = " • "
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = tags,
            color = Color(0xFFa1a1a1),
            fontSize = 12.sp
        )
    }
}

@Preview
@Composable
fun RestItemPreview() {
    LunchAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            RestaurantItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                item = RestaurantsMock.data.first()
            )
        }
    }
}