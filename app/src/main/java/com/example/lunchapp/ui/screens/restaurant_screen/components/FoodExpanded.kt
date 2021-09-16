package com.example.lunchapp.ui.screens.restaurant_screen.components

import androidx.compose.animation.core.*
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.lunchapp.domain.data.RestaurantMenuMock
import com.example.lunchapp.model.Food
import com.example.lunchapp.ui.theme.AppColors
import com.example.lunchapp.ui.theme.LunchAppTheme

enum class ExpandedFoodState {
    Initial, Expanded, Cancelled, Added
}

@Composable
fun FoodExpanded(
    modifier: Modifier = Modifier,
    food: Food,
    onAddClick: (Food) -> Unit,

    ) {
    val imageWidth = (LocalConfiguration.current.screenWidthDp.dp) * 0.6f
    val imageHeight = imageWidth * 0.6f
    var specialRequest by remember { mutableStateOf("") }

//    var cState = remember { MutableTransitionState(ExpandedFoodState.Initial) }
//    cState.targetState = ExpandedFoodState.Expanded
//    val transition = updateTransition(cState, label = "")
    var cState by remember { mutableStateOf(ExpandedFoodState.Initial) }
    val transition = updateTransition(cState, label = "")

//    val spec = tween(
//        durationMillis = 600
//    )

    LaunchedEffect(Unit) {
        cState = ExpandedFoodState.Expanded
    }

    val animationDuration = 400

    val imageScale by transition.animateFloat(label = "",
        transitionSpec = {
            tween(
                durationMillis = animationDuration,
                easing = LinearEasing
            )
        }) { state ->
        when (state) {
            ExpandedFoodState.Initial -> 0.2f
            else -> 1f
        }
    }
    val imageAlpha by transition.animateFloat(label = "") { state ->
        when (state) {
            ExpandedFoodState.Initial -> 0f
            else -> 1f
        }
    }
    val imageOffset by transition.animateDp(label = "",
        transitionSpec = {
            tween(
                durationMillis = animationDuration,
            )
        }) { state ->
        when (state) {
            ExpandedFoodState.Initial -> (-80).dp
            ExpandedFoodState.Added -> 300.dp
            else -> 0f.dp
        }
    }

    val cardScaleY by transition.animateFloat(label = "",
        transitionSpec = {
            tween(
                durationMillis = 300
            )
        }
    ) { state ->
        when (state) {
            ExpandedFoodState.Initial -> 0.5f
            else -> 1f
        }
    }

    val textInitialOffset = 24.dp
    val textDuration = 200

    val titleOffset by transition.animateDp(
        label = "",
        transitionSpec = {
            tween(durationMillis = textDuration, delayMillis = 200)
        }
    ) { state ->
        when (state) {
            ExpandedFoodState.Initial -> textInitialOffset
            else -> 0.dp
        }
    }
    val titleAlpha by transition.animateFloat(
        label = "",
        transitionSpec = {
            tween(durationMillis = textDuration, delayMillis = 200)
        }
    ) { state ->
        when (state) {
            ExpandedFoodState.Initial -> 0f
            else -> 1f
        }
    }

    val infoOffset by transition.animateDp(
        label = "",
        transitionSpec = {
            tween(durationMillis = textDuration, delayMillis = 250)
        }
    ) { state ->
        when (state) {
            ExpandedFoodState.Initial -> textInitialOffset
            else -> 0.dp
        }
    }
    val infoAlpha by transition.animateFloat(
        label = "",
        transitionSpec = {
            tween(durationMillis = textDuration, delayMillis = 250)
        }
    ) { state ->
        when (state) {
            ExpandedFoodState.Initial -> 0f
            else -> 1f
        }
    }

    val priceOffset by transition.animateDp(
        label = "",
        transitionSpec = {
            tween(durationMillis = textDuration, delayMillis = 300)
        }
    ) { state ->
        when (state) {
            ExpandedFoodState.Initial -> textInitialOffset
            else -> 0.dp
        }
    }
    val priceAlpha by transition.animateFloat(
        label = "",
        transitionSpec = {
            tween(durationMillis = textDuration, delayMillis = 300)
        }
    ) { state ->
        when (state) {
            ExpandedFoodState.Initial -> 0f
            else -> 1f
        }
    }

    val descOffset by transition.animateDp(
        label = "",
        transitionSpec = {
            tween(durationMillis = textDuration, delayMillis = 350)
        }
    ) { state ->
        when (state) {
            ExpandedFoodState.Initial -> textInitialOffset
            else -> 0.dp
        }
    }
    val descAlpha by transition.animateFloat(
        label = "",
        transitionSpec = {
            tween(durationMillis = textDuration, delayMillis = 350)
        }
    ) { state ->
        when (state) {
            ExpandedFoodState.Initial -> 0f
            else -> 1f
        }
    }

    val buttonOffset by transition.animateDp(
        label = "",
        transitionSpec = {
            tween(durationMillis = textDuration, delayMillis = 400)
        }
    ) { state ->
        when (state) {
            ExpandedFoodState.Initial -> textInitialOffset
            else -> 0.dp
        }
    }
    val buttonAlpha by transition.animateFloat(
        label = "",
        transitionSpec = {
            tween(durationMillis = textDuration, delayMillis = 400)
        }
    ) { state ->
        when (state) {
            ExpandedFoodState.Initial -> 0f
            else -> 1f
        }
    }

    val addXPos = LocalConfiguration.current.screenWidthDp.dp * 0.9f
    val addYPos = LocalConfiguration.current.screenHeightDp.dp * 0.9f
    val addXPosPx = with(LocalDensity.current) { addXPos.toPx() }
    val addYPosPx = with(LocalDensity.current) { addYPos.toPx() }

    var locBoxOffsetX = 0f
    var locBoxOffsetY = 0f

    val contentOffset by transition.animateIntOffset(label = "") { state ->
        when (state) {
//            ExpandedFoodState.Added -> IntOffset(locBoxOffsetX.toInt(), locBoxOffsetY.toInt())
            ExpandedFoodState.Added -> IntOffset(200, 400)
            else -> IntOffset(0, 0)
        }
    }
    val contentScale by transition.animateFloat(label = "") { state ->
        when (state) {
            ExpandedFoodState.Added -> 0.1f
            else -> 1f
        }
    }

    Box(
        modifier = modifier
            .onGloballyPositioned {
                val boxPosOffset = it.positionInWindow()
//                val localOffsets = it.windowToLocal(Offset(addXPosPx.toFloat(), addYPosPx.toFloat()))
                locBoxOffsetX = addXPosPx - boxPosOffset.x
                locBoxOffsetY = addYPosPx - boxPosOffset.y
            }
            .offset { contentOffset }
            .scale(contentScale)
    ) {
        Image(
            painter = painterResource(id = food.img),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .zIndex(1f)
                .offset(y = imageOffset)
                .scale(imageScale)
                .alpha(imageAlpha)
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
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(scaleX = 1f, scaleY = cardScaleY),
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
                        modifier = Modifier
                            .offset(y = titleOffset)
                            .alpha(titleAlpha),
                        text = food.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    FoodTags(
                        modifier = Modifier
                            .padding(4.dp)
                            .offset(y = infoOffset)
                            .alpha(infoAlpha),
                        food = food
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 4.dp, bottom = 8.dp)
                            .offset(y = priceOffset)
                            .alpha(priceAlpha),
                        text = "${food.price} €",
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                            .offset(y = descOffset)
                            .alpha(descAlpha),
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
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .offset(y = descOffset)
                            .alpha(descAlpha),
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
                            )
                            .offset(y = buttonOffset)
                            .alpha(buttonAlpha),
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
//                            onAddClick(food)
                            cState = ExpandedFoodState.Added
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