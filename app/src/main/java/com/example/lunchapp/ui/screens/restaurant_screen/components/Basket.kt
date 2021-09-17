package com.example.lunchapp.ui.screens.restaurant_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lunchapp.ui.theme.AppColors
import com.example.lunchapp.ui.theme.LunchAppTheme

@ExperimentalAnimationApi
@Composable
fun Basket(
    modifier: Modifier,
    itemsCount: Int
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        FloatingActionButton(
            onClick = {

            }
        ) {
            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "")
        }
        AnimatedVisibility(visible = (itemsCount > 0)) {
            val radius = with(LocalDensity.current) { 9.dp.toPx() }
            Text(
                modifier = Modifier
                    .offset(x = 8.dp, y = (-8).dp)
                    .padding(12.dp)
                    .drawBehind {
                        drawCircle(
                            color = Color.White,
                            radius = radius,
                        )
                        drawCircle(
                            style = Stroke(width = 1f),
                            radius = radius,
                            color = Color.Red
                        )
                    },
                color = MaterialTheme.colors.secondary,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                text = itemsCount.toString()
            )
        }
    }

}

@ExperimentalAnimationApi
@Preview
@Composable
fun BasketPreview() {
    LunchAppTheme {
        Surface(color = AppColors.background) {
            Basket(modifier = Modifier.padding(24.dp), itemsCount = 4)
        }
    }
}