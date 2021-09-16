package com.example.lunchapp.ui.screens.restaurant_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.BorderColor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.lunchapp.domain.data.RestaurantMenuMock
import com.example.lunchapp.model.Food
import com.example.lunchapp.ui.theme.AppColors
import com.example.lunchapp.ui.theme.LunchAppTheme

@Composable
fun FoodExpanded(
    modifier: Modifier = Modifier,
    food: Food,
    onAddClick: (Food) -> Unit
) {
    val imageWidth = (LocalConfiguration.current.screenWidthDp.dp) * 0.6f
    val imageHeight = imageWidth * 0.6f
    var specialRequest by remember { mutableStateOf("") }

    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = food.img),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .zIndex(1f)
                .size(imageWidth, imageHeight)
                .clip(RoundedCornerShape(16.dp))
                .align(Alignment.TopCenter)
        )
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .zIndex(0f)
                .width(imageWidth * 1.15f)
        ) {
            Spacer(Modifier.height(36.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                backgroundColor = MaterialTheme.colors.surface
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(imageHeight - 20.dp))
                    Text(
                        text = food.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    FoodTags(
                        modifier = Modifier.padding(4.dp),
                        food = food
                    )
                    Text(
                        modifier = Modifier.padding(top = 4.dp, bottom = 8.dp),
                        text = "${food.price} €",
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        text = food.ingredients.joinToString(
                            separator = ", "
                        ),
                        color = Color(0xff8d8d8d),
                        fontSize = 14.sp
                    )
                    TextField(
                        value = specialRequest,
                        onValueChange = { specialRequest = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp),
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            cursorColor = Color.LightGray,
                            leadingIconColor = MaterialTheme.colors.secondary,
                            placeholderColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.BorderColor,
                                contentDescription = ""
                            )
                        },
                        placeholder = {
                            Text(
                                "Add special request",
                                fontSize = 14.sp
                            )
                        }
                    )
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 8.dp,
                                bottom = 16.dp, start = 16.dp, end = 16.dp
                            ),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.secondary,
                            disabledBackgroundColor = MaterialTheme.colors
                                .secondary.copy(alpha = ContentAlpha.disabled)
                        ),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 4.dp
                        ),
                        onClick = {
                            onAddClick(food)
                        }
                    ) {
                        Box(Modifier.fillMaxWidth()) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "",
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(end = 4.dp),
                                tint = MaterialTheme.colors.surface
                            )
                            Text(
                                text = "add to basket".toUpperCase(Locale.current),
                                letterSpacing = 1.sp,
                                color = MaterialTheme.colors.surface,
                                modifier = Modifier.align(Alignment.Center),
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun FoodExpandedPreview() {
    LunchAppTheme {
        Surface(color = AppColors.background) {
            FoodExpanded(
                modifier = Modifier
                    .padding(24.dp),
                food = RestaurantMenuMock.data.first().foods.first(),
                onAddClick = {}
            )
        }
    }

}