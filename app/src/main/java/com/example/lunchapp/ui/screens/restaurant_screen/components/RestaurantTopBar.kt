package com.example.lunchapp.ui.screens.restaurant_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lunchapp.R

@Composable
fun RestaurantTopBar(
    onBackClick: () -> Unit,
    onProfileClick: () -> Unit,
    title: String
) {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = Color.Transparent
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier.clickable {
                    onBackClick()
                },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = ""
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                text = title
            )
            Image(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically)
                    .clickable {
                        onProfileClick()
                    },
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.ava), contentDescription = ""
            )
        }
    }
}