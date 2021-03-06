package com.example.lunchapp.ui.screens.restaurant_screen.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.DismissDirection.*
import androidx.compose.material.DismissValue.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lunchapp.ui.theme.AppColors
import com.example.lunchapp.ui.theme.LunchAppTheme


data class Item(val id: Int, val text: String)

@ExperimentalTransitionApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun SwipeT() {
    val k = (1..30).map {
        Item(id = it, text = "Item $it")
    }.toList()
    val stateList = remember {
        mutableStateListOf<Item>().apply {
            addAll(k)
        }
    }

    LazyColumn() {
        items(items = stateList, key = { it.id }) { item ->
            val vAlpha = remember { Animatable(1f) }
            val yScale = remember { Animatable(1f) }

            val visibleState = remember {
                MutableTransitionState(true)
            }

            var unread by remember { mutableStateOf(false) }
            val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissedToEnd) unread = !unread
                        if (it == DismissedToStart) {
                            visibleState.targetState = false
                        }
                        it != DismissedToEnd
                    }
            )

            AnimatedVisibility(
                    visibleState = visibleState,
                    exit = fadeOut() + shrinkVertically()
            ) {
                SwipeToDismiss(
                        state = dismissState,
                        modifier = Modifier
                                .border(1.dp, Color.Red)
                                .graphicsLayer(
                                        scaleY = yScale.value,
                                        alpha = vAlpha.value
                                )
                                .padding(vertical = 4.dp),
                        directions = setOf(StartToEnd, DismissDirection.EndToStart),
                        dismissThresholds = { direction ->
                            FractionalThreshold(if (direction == StartToEnd) 0.25f else 0.35f)
                        },
                        background = {
                            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
                            val color by animateColorAsState(
                                    when (dismissState.targetValue) {
                                        Default -> Color.LightGray
                                        DismissedToEnd -> Color.Green
                                        DismissedToStart -> Color.Red
                                    }
                            )
                            val alignment = when (direction) {
                                StartToEnd -> Alignment.CenterStart
                                EndToStart -> Alignment.CenterEnd
                            }
                            val icon = when (direction) {
                                StartToEnd -> Icons.Default.Done
                                EndToStart -> Icons.Default.Delete
                            }
                            val scale by animateFloatAsState(
                                    if (dismissState.targetValue == Default) 0.75f else 1f
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
                            Card(
                                    elevation = animateDpAsState(
                                            if (dismissState.dismissDirection != null) 4.dp else 0.dp
                                    ).value
                            ) {
                                ListItem(
                                        text = {
                                            Text(
                                                    item.text,
                                                    fontWeight = if (unread) FontWeight.Bold else null
                                            )
                                        },
                                        secondaryText = { Text("Swipe me left or right!") }
                                )
                            }
                        }
                )
                Spacer(Modifier.height(8.dp))
            }

            if (visibleState.isIdle && !visibleState.targetState) {
                stateList.remove(item)
            }
        }
    }
}

@ExperimentalTransitionApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Preview
@Composable
fun SwipePreview() {
    LunchAppTheme {
        Surface(color = AppColors.background) {
            SwipeT()
        }
    }
}
