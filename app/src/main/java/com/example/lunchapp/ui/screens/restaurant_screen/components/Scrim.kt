package com.example.lunchapp.ui.screens.restaurant_screen.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Scrim(
    modifier: Modifier = Modifier,
    open: Boolean,
    onClose: () -> Unit,
    alpha: Float,
    scrimContent: @Composable () -> Unit
) {
    val dismissDrawer = if (open) {
        Modifier
            .pointerInput(onClose) { detectTapGestures { onClose() } }
    } else {
        Modifier
    }
    Box(
        modifier
            .fillMaxSize()
            .then(dismissDrawer)
            .graphicsLayer(alpha = alpha)
    ) {
        Canvas(
            Modifier.matchParentSize()
        ) {
            drawRect(color = Color.Black.copy(alpha = 0.85f))
        }
        scrimContent()
    }
}

@Composable
fun ScrimContent(modifier: Modifier = Modifier) {
    val alpha = remember { Animatable(0f) }
    Box(
        modifier = modifier
            .alpha(alpha.value)
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
                tint = color
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
