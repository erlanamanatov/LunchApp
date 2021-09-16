package com.example.lunchapp.ui.screens.mainscreen.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.lunchapp.domain.data.RestaurantMenuMock
import com.example.lunchapp.model.Food
import com.example.lunchapp.ui.theme.LunchAppTheme
import org.intellij.lang.annotations.JdkConstants

@ExperimentalMaterialApi
@Composable
fun BestDeals(
    modifier: Modifier = Modifier,
    foods: List<Food>
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(start = 24.dp, bottom = 16.dp),
            text = "Today's best deals",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        Row(Modifier.horizontalScroll(rememberScrollState())) {
            foods.forEachIndexed { index, food ->
                BestDealItem(
                    modifier = Modifier.offset(x = 24.dp),
                    food = food
                )
                Spacer(Modifier.width(16.dp))
            }
            Spacer(Modifier.width(32.dp))
//            for (food in foods) {
//                BestDealItem(food = food)
//                Spacer(Modifier.width(16.dp))
//            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun BestDealItem(
    modifier: Modifier = Modifier,
    food: Food
) {
    val corner = 8.dp
    Box(
        modifier
            .size(200.dp, 170.dp)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f)
                .height(120.dp)
                .padding(horizontal = 8.dp)
                .clip(RoundedCornerShape(corner))
                .drawBehind {
                    drawRect(
                        color = Color.Red,
                        size = Size(size.width, size.height)
                    )
                }
                .align(Alignment.TopCenter),
            painter = painterResource(id = food.img),
            contentScale = ContentScale.Crop,
            contentDescription = ""
        )
        Card(
            modifier = Modifier
                .zIndex(0f)
                .fillMaxWidth()
                .height(135.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(corner),
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 4.dp
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = food.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        food.restaurant?.let { restName ->
                            Text(
                                text = restName,
                                fontSize = 12.sp,
                                color = Color(0xFFb6b6b6),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = "${food.price} €",
                        fontSize = 16.sp,
                        color = Color(0xFF767676)
                    )
                }
            }
        }

    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun BestDealItemPreview() {
    LunchAppTheme {
        Surface(color = Color.LightGray) {
            BestDealItem(
                modifier = Modifier.padding(25.dp),
                food = RestaurantMenuMock.data.first().foods.first().copy(restaurant = "Mioto")
            )
        }
    }

}