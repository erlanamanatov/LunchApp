package com.example.lunchapp.ui.screens.restaurant_screen.components

import androidx.compose.animation.*
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.DismissDirection.EndToStart
import androidx.compose.material.DismissDirection.StartToEnd
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lunchapp.domain.data.RestaurantMenuMock
import com.example.lunchapp.model.Food
import com.example.lunchapp.ui.theme.AppColors
import com.example.lunchapp.ui.theme.LunchAppTheme

@ExperimentalTransitionApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun ColumnScope.BasketContent(
        foodItems: List<Food>,
        onItemRemove: (Food) -> Unit,
        onConfirmClick: () -> Unit
) {
    BasketHeader()
    Divider(Modifier.fillMaxWidth())
    FoodItems(foodItems = foodItems,
            onItemDelete = onItemRemove)
    BasketTotalAmount(
            totalAmount = foodItems
                    .map { it.price }
                    .fold(0f, { acc, fl -> acc + fl })
    )
    BasketDeliveryTime()
    ConfirmOrderButton(
            onClick = onConfirmClick
    )
}

@ExperimentalTransitionApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
private fun FoodItems(
        modifier: Modifier = Modifier,
        foodItems: List<Food>,
        onItemDelete: (Food) -> Unit
) {
    Column(modifier = modifier) {
        for (food in foodItems) {
            key(food.name, food.info) {
                val visibleState = remember {
                    MutableTransitionState(true)
                }
                val dismissState = rememberDismissState {
                    if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart)
                        visibleState.targetState = false
                    it != DismissValue.Default
                }

                AnimatedVisibility(visibleState = visibleState,
                        exit = fadeOut() + shrinkVertically()
                ) {
                    SwipeToDismiss(
                            state = dismissState,
                            directions = setOf(StartToEnd, EndToStart),
                            dismissThresholds = { FractionalThreshold(0.3f) },
                            background = {
                                val direction = dismissState.dismissDirection
                                        ?: return@SwipeToDismiss
                                val color by animateColorAsState(
                                        when (dismissState.targetValue) {
                                            DismissValue.Default -> Color.LightGray
                                            else -> Color.Red
                                        }
                                )
                                val alignment = when (direction) {
                                    StartToEnd -> Alignment.CenterStart
                                    EndToStart -> Alignment.CenterEnd
                                }
                                val icon = Icons.Default.Delete
                                val scale by animateFloatAsState(
                                        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                                )
                                Box(
                                        Modifier
                                                .fillMaxSize()
                                                .background(color)
                                                .padding(horizontal = 20.dp),
                                        contentAlignment = alignment
                                ) {
                                    Icon(
                                            icon,
                                            contentDescription = "Localized description",
                                            modifier = Modifier.scale(scale)
                                    )
                                }
                            },
                            dismissContent = {
                                FoodSheetItem(
                                        modifier = Modifier.fillMaxWidth(),
                                        food = food
                                )
                            }
                    )
                }
                Divider(Modifier.fillMaxWidth())
                if (visibleState.isIdle && !visibleState.targetState) {
                    onItemDelete(food)
                }
            }
        }
    }
}

@Composable
private fun ConfirmOrderButton(
        modifier: Modifier = Modifier,
        onClick: () -> Unit
) {
    Button(
            modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 24.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary
            ),
            elevation = ButtonDefaults.elevation(defaultElevation = 4.dp),
            onClick = onClick
    ) {
        Text(
                modifier = Modifier.padding(vertical = 12.dp),
                fontWeight = FontWeight.Bold,
                text = "confirm order".toUpperCase(Locale.current),
                color = MaterialTheme.colors.surface,
                letterSpacing = 1.sp,
                fontSize = 20.sp
        )
    }
}

@Composable
private fun ColumnScope.BasketDeliveryTime() {
    val lightColor = Color(0xffbfc0c6)
    Row(
            modifier = Modifier.Companion
                    .align(CenterHorizontally)
                    .padding(vertical = 16.dp),
            verticalAlignment = CenterVertically
    ) {
        Icon(
                imageVector = Icons.Default.Schedule,
                contentDescription = "",
                tint = lightColor
        )
        Text(
                text = "Delivery time",
                modifier = Modifier.padding(horizontal = 8.dp),
                color = lightColor,
                fontSize = 14.sp
        )
        Text(
                text = "13:00 - 13.:30",
                fontSize = 16.sp
        )
    }
}

@Composable
private fun BasketTotalAmount(
        modifier: Modifier = Modifier,
        totalAmount: Float) {
    Row(
            modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = SpaceBetween,
            verticalAlignment = CenterVertically
    ) {
        Text(
                text = "Total Amount",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
        )
        Text(
                text = "$totalAmount €",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ColumnScope.BasketHeader(modifier: Modifier = Modifier) {
    Box(
            modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(top = 12.dp, bottom = 8.dp)
                    .size(64.dp, 6.dp)
                    .background(color = Color.LightGray, shape = RoundedCornerShape(50))
    )
    Row(
            modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = SpaceBetween,
            verticalAlignment = CenterVertically
    ) {
        Row(verticalAlignment = CenterVertically) {
            Icon(
                    modifier = Modifier.scale(1.2f),
                    imageVector = Icons.Default.ShoppingCart, contentDescription = "",
                    tint = MaterialTheme.colors.secondary
            )
            Text(
                    modifier = Modifier.padding(start = 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    text = "Your Basket"
            )
        }
        Text(
                modifier = Modifier
                        .background(color = Color(0xffafb0bc), shape = RoundedCornerShape(50))
                        .padding(vertical = 4.dp, horizontal = 16.dp),
                color = MaterialTheme.colors.surface,
                text = "clear".toUpperCase(Locale.current),
                fontSize = 9.sp
        )
    }
}

@Composable
fun FoodSheetItem(
        modifier: Modifier = Modifier,
        food: Food
) {
    Row(
            modifier = modifier
                    .background(color = AppColors.background)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                    text = food.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black.copy(alpha = 0.75f)
            )
            Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = food.info,
                    fontSize = 12.sp,
                    color = Color.Black.copy(alpha = 0.7f)
            )
        }
        Text(
                text = "${food.price} €",
                fontSize = 14.sp,
                color = Color.Black.copy(alpha = 0.7f)
        )
    }
}


@Preview
@Composable
fun FoodSheetItemPreview() {
    LunchAppTheme {
        Surface(color = AppColors.background) {
            FoodSheetItem(
                    modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                    food = RestaurantMenuMock.data.first().foods.first()
            )
        }
    }
}

@Preview
@Composable
fun SheetPreview() {
    LunchAppTheme {
        val foods = RestaurantMenuMock.data.first().foods
        androidx.compose.material.Surface(color = AppColors.background) {
            Column(Modifier.fillMaxWidth()) {
//                SheetContent(foodItems = RestaurantMenuMock.data.first().foods)
//                Text("dfsdfsd")
            }
        }
    }

}
