package com.example.lunchapp.ui.screens.mainscreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lunchapp.ui.theme.LunchAppTheme

@ExperimentalMaterialApi
@Composable
fun SearchComponent(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(modifier = modifier) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            value = value,
            onValueChange = onValueChange
        )
        Spacer(Modifier.height(12.dp))
        AppliedFilters(
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}


@Composable
private fun searchFieldColors() = TextFieldDefaults.textFieldColors(
    backgroundColor = MaterialTheme.colors.surface,
    leadingIconColor = MaterialTheme.colors.secondary,
    placeholderColor = Color.Black.copy(alpha = 0.9f),
    unfocusedIndicatorColor = Color.Transparent
)

@ExperimentalMaterialApi
@Composable
private fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = modifier
    ) {
        Card(
            modifier = Modifier.weight(1f),
            elevation = 12.dp
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = { Text("Search for restaurant") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = ""
                    )
                },
                colors = searchFieldColors()
            )
        }
        Spacer(Modifier.width(8.dp))
        Card(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f),
            elevation = 12.dp,
            onClick = {}
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Default.Tune,
                    contentDescription = "",
                    tint = MaterialTheme.colors.secondary
                )
            }
        }
    }
}

@Composable
fun AppliedFilters(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ) {
        FilterItem(
            modifier = Modifier.weight(1f),
            image = Icons.Filled.Business,
            filter = "Krakov Office"
        )
        Spacer(Modifier.width(24.dp))
        FilterItem(
            modifier = Modifier.weight(1f),
            image = Icons.Filled.Schedule,
            filter = "Anytime"
        )
    }
}


@Composable
private fun FilterItem(
    modifier: Modifier = Modifier,
    image: ImageVector,
    filter: String
) {
    val tintColor = Color(0xFF7a7a7a)
    Card(
        modifier = modifier,
        elevation = 12.dp,
        backgroundColor = Color(0xFFf5f5f5)
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = image,
                contentDescription = "",
                tint = tintColor
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                text = filter,
                color = tintColor,
                style = MaterialTheme.typography.subtitle2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    var textToSearch by remember { mutableStateOf("") }
    LunchAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            SearchComponent(
                modifier = Modifier.padding(20.dp),
                value = textToSearch,
                onValueChange = { textToSearch = it }
            )
        }
    }
}
