package com.example.lunchapp.ui.screens.restaurant_screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.lunchapp.domain.data.RestaurantMenuMock
import com.example.lunchapp.model.Food
import com.example.lunchapp.ui.screens.restaurant_screen.components.*
import com.example.lunchapp.ui.theme.AppColors
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun RestaurantScreen(modifier: Modifier = Modifier) {
    val basketItems = remember { mutableStateListOf<Food>() }
    var expandedItem by remember { mutableStateOf<Food?>(null) }

    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    BackHandler(
            enabled = expandedItem != null || sheetState.isVisible
    ) {
        if (expandedItem != null) {
            expandedItem = null
            return@BackHandler
        }
        scope.launch {
            sheetState.hide()
        }
    }

    var expandedRect by remember { mutableStateOf(Rect.Zero) }
    var basketRect by remember { mutableStateOf(Rect.Zero) }

    var added by remember { mutableStateOf(false) }
    val expandedOffsetX = remember { Animatable(0f) }
    val expandedOffsetY = remember { Animatable(0f) }
    val expandedScale = remember { Animatable(1f) }
    val centerFab = remember(basketRect) {
        basketRect.center
    }

    LaunchedEffect(added) {
        if (added) {
            val animSpec = tween<Float>(durationMillis = 600)
            val j = launch {
                expandedOffsetX.animateTo(
                        centerFab.x - expandedRect.topLeft.x - expandedRect.width / 2f,
                        animationSpec = animSpec
                )
            }
            val j2 =
                    launch {
                        expandedOffsetY.animateTo(
                                centerFab.y - expandedRect.topLeft.y - expandedRect.height / 2f,
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


    ModalBottomSheetLayout(
            sheetContent = {
                BasketContent(
                        foodItems = basketItems.toList(),
                        onItemRemove = { basketItems.remove(it) },
                        onConfirmClick = {}
                )
            },
            sheetState = sheetState,
            sheetShape = RoundedCornerShape(topStartPercent = 10, topEndPercent = 10)
    ) {
        Surface(color = AppColors.background, modifier = modifier) {
            Box(modifier = Modifier.fillMaxSize()) {

                expandedItem?.let {
                    Scrim(
                            modifier = Modifier
                                    .zIndex(5f),
                            onClose = { expandedItem = null },
                            open = true,
                            alpha = expandedScale.value
                    ) {
                        ScrimContent(Modifier.matchParentSize())
                    }
                    FoodExpanded(
                            food = expandedItem!!,
                            modifier = Modifier
                                    .align(Alignment.Center)
                                    .zIndex(6f)
                                    .wrapContentSize(align = Alignment.TopStart)
                                    .graphicsLayer(
                                            translationX = expandedOffsetX.value,
                                            translationY = expandedOffsetY.value,
                                            scaleX = expandedScale.value,
                                            scaleY = expandedScale.value
                                    ),
                            onAddClick = {
                                added = true
                                basketItems.add(it)
                            },
                            onPositionedRect = { rect ->
                                expandedRect = rect
                            }
                    )
                }
                Basket(
                        modifier = Modifier
                                .zIndex(1f)
                                .align(Alignment.BottomEnd)
                                .padding(end = 36.dp, bottom = 36.dp)
                                .onGloballyPositioned {
                                    basketRect = it.boundsInRoot()
                                },
                        itemsCount = basketItems.size,
                        onClick = {
                            scope.launch {
                                sheetState.show()
                            }
                        }
                )


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
                                    },
                                    onAddToBasketClick = {
                                        basketItems.add(it)
                                    },
                                    basket = basketItems.toList()
                            )
                        }
                        item {
                            Spacer(Modifier.height(36.dp + 56.dp))
                        }
                    }
                }
            }
        }
    }
}



