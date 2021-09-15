package com.example.lunchapp.ui.screens.mainscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
fun TopBar() {
    TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
        Text(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            text = "Lunching"
        )
        Image(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .align(Alignment.CenterVertically),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.ava), contentDescription = ""
        )
    }
}
