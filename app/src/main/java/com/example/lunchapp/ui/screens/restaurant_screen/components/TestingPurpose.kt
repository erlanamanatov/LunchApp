package com.example.lunchapp.ui.screens.restaurant_screen.components

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lunchapp.ui.theme.AppColors
import com.example.lunchapp.ui.theme.LunchAppTheme

// This is an example of a list of dismissible items, similar to what you would see in an
// email app. Swiping left reveals a 'delete' icon and swiping right reveals a 'done' icon.
// The background will start as grey, but once the dismiss threshold is reached, the colour
// will animate to red if you're swiping left or green if you're swiping right. When you let
// go, the item will animate out of the way if you're swiping left (like deleting an email) or
// back to its default position if you're swiping right (like marking an email as read/unread).

data class Item(val id: Int, val text: String)

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun SwipeT() {
    val k = (1..30).map {
        Item(id = it, text = "Item $it")
    }.toList()
    var itemsMutable = remember {
        (1..30).map {
            "Item $it"
        }.toMutableList()
    }
    val items by remember {
        mutableStateOf(
            itemsMutable.toList()
        )
    }
    val stateList = remember {
        mutableStateListOf<Item>().apply {
            addAll(k)
        }
    }
    LazyColumn(modifier = Modifier.animateContentSize()) {
//        itemsIndexed(stateList) { index, item ->
        items(items = stateList, key = { it.id }) { item ->
            var unread by remember { mutableStateOf(false) }
            var deleted by remember { mutableStateOf(false) }
            val dismissState = rememberDismissState(
                confirmStateChange = {
                    if (it == DismissedToEnd) unread = !unread
                    if (it == DismissedToStart) {
                        deleted = !deleted
                    }
//                    false
                    it !=DismissedToEnd
                }
            )
            AnimatedVisibility(
                visible = !deleted,
                exit = shrinkVertically()
            ) {
                SwipeToDismiss(
                    state = dismissState,
                    modifier = Modifier.padding(vertical = 4.dp),
                    directions = setOf(StartToEnd, DismissDirection.EndToStart),
                    dismissThresholds = { direction ->
                        FractionalThreshold(if (direction == StartToEnd) 0.25f else 0.5f)
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
            }
        }
    }
}

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
