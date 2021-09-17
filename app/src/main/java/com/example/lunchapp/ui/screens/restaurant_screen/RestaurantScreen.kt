package com.example.lunchapp.ui.screens.restaurant_screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.lunchapp.domain.data.RestaurantMenuMock
import com.example.lunchapp.model.Food
import com.example.lunchapp.ui.screens.restaurant_screen.components.FoodExpanded
import com.example.lunchapp.ui.screens.restaurant_screen.components.MenuCategoryItem
import com.example.lunchapp.ui.screens.restaurant_screen.components.RestaurantTopBar
import com.example.lunchapp.ui.theme.AppColors
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun RestaurantScreen(modifier: Modifier = Modifier) {
    var expandedItem by remember { mutableStateOf<Food?>(null) }
    BackHandler(
        enabled = expandedItem != null
    ) {
        expandedItem = null
    }

    var expandedRect by remember { mutableStateOf(Rect(Offset(0f, 0f), Offset(0f, 0f))) }
    var added by remember { mutableStateOf(false) }
    val expandedOffsetX = remember { Animatable(0f) }
    val expandedOffsetY = remember { Animatable(0f) }
    val expandedScale = remember { Animatable(1f) }
    val addXPos = LocalConfiguration.current.screenWidthDp.dp * 0.9f
    val addYPos = LocalConfiguration.current.screenHeightDp.dp * 0.9f
    val addXPosPx = with(LocalDensity.current) { addXPos.toPx() }
    val addYPosPx = with(LocalDensity.current) { addYPos.toPx() }

    LaunchedEffect(added) {
        if (added) {
            val animSpec = tween<Float>(durationMillis = 600)
            val j = launch {
                expandedOffsetX.animateTo(
                    addXPosPx - expandedRect.topLeft.x - expandedRect.width / 2f,
                    animationSpec = animSpec
                )
            }
            val j2 =
                launch {
                    expandedOffsetY.animateTo(
                        addYPosPx - expandedRect.topLeft.y - expandedRect.height / 2f,
                        animationSpec = animSpec
                    )
                }
            val j3 = launch {
                expandedScale.animateTo(
                    0f,
                    animationSpec = animSpec
                )
            }
            joinAll(j, j2, j3)
            expandedItem = null
            expandedOffsetX.snapTo(0f)
            expandedOffsetY.snapTo(0f)
            expandedScale.snapTo(1f)
            added = false
        }
    }

    Surface(color = AppColors.background) {
        Box(modifier = modifier) {
            expandedItem?.let {
                Film(
                    modifier = Modifier
                        .fillMaxSize()
                        .zIndex(1f)
                        .alpha(expandedScale.value),
                    onCloseClick = { expandedItem = null }
                )
                FoodExpanded(
                    food = expandedItem!!,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .zIndex(2f)
                        .wrapContentSize(align = Alignment.TopStart)
                        .graphicsLayer(
                            translationX = expandedOffsetX.value,
                            translationY = expandedOffsetY.value,
                            scaleX = expandedScale.value,
                            scaleY = expandedScale.value,

                        ),
                    onAddClick = {
                        added = true
                    },
                    onPositionedRect = { rect ->
                        expandedRect = rect
                    }
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(0f)
            ) {
                RestaurantTopBar(
                    onBackClick = {},
                    onProfileClick = {},
                    title = "Gusto Ristorante"
                )
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    itemsIndexed(RestaurantMenuMock.data) { index, item ->
                        val itModifier = if (index == 0) Modifier else
                            Modifier.padding(top = 16.dp)
                        MenuCategoryItem(
                            modifier = itModifier,
                            item = item,
                            onItemClick = {
                                expandedItem = it
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Film(modifier: Modifier, onCloseClick: () -> Unit) {
    val alpha = remember { androidx.compose.animation.core.Animatable(0f) }
    Box(
        modifier = modifier
            .alpha(alpha.value)
            .background(color = Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(start = 8.dp, top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val color = Color(0xFFeeeeee)
            Icon(
                imageVector = Icons.Default.Close, contentDescription = "",
                tint = color,
                modifier = Modifier.clickable {
                    onCloseClick()
                }
            )
            Text(
                modifier = Modifier.weight(1f),
                text = "Gusto Ristorante",
                color = color,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        }
    }

    LaunchedEffect(Unit) {
        alpha.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 200
            )
        )
    }
}

