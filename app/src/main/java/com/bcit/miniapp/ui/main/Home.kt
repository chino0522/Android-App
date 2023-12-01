package com.bcit.miniapp.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class FavoriteItem(
    val imageUrl: String,
    val fact: String
)


@Composable
fun Home(catState: CatState, favourites: MutableList<FavoriteItem>) {
    var isRefreshing by remember { mutableStateOf(false) }
    var isFavorite by remember { mutableStateOf(false) }

    var favouriteItem: FavoriteItem

    LaunchedEffect(isRefreshing) {
        if (isRefreshing) {
            catState.refresh()
            isRefreshing = false
        }
    }

    // Check if the current cat image is in the list of favorites
    isFavorite = favourites.any { it.imageUrl == catState.image.value }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFECF4D6))
    ) {
        LazyColumn(
            content = {
                item {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp)
                                .padding(start = 10.dp, end = 10.dp)
                                .drawBehind {
                                    drawLine(
                                        color = Color.Black,
                                        strokeWidth = 4f,
                                        start = Offset(15f, size.height - 15f), // Start slightly inside the left bottom corner
                                        end = Offset(size.width - 15f, size.height - 15f) // End slightly inside the right bottom corner
                                    )
                                },
                            model = catState.image.value,
                            contentDescription = null
                        )
                        IconButton(
                            modifier = Modifier.align(Alignment.Start),
                            onClick = {
                                isFavorite = !isFavorite
                                favouriteItem = FavoriteItem(
                                    imageUrl = catState.image.value,
                                    fact = catState.fact.value[0]
                                )
                                if (isFavorite) {
                                    favourites.add(
                                        favouriteItem
                                    )
                                } else {
                                    favourites.remove(favouriteItem)
                                }
                            }
                        ) {
                            Icon(
                                if (isFavorite) {
                                    Icons.Default.Favorite
                                } else {
                                    Icons.Outlined.FavoriteBorder
                                },
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(top = 5.dp, start = 10.dp),
                                contentDescription = null
                            )
                        }
                        Text(
                            modifier = Modifier.padding(18.dp),
                            text = catState.fact.value[0],
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start
                        )
                        Button(
                            modifier = Modifier
                                .padding(top = 30.dp),
                            onClick = {
                                isRefreshing = true
                            }
                        ) {
                            Text(text = "Next")
                        }

                    }
                }
            }
        )
    }
}

