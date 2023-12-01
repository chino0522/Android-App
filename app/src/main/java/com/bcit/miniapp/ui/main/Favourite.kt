package com.bcit.miniapp.ui.main

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun Favourite(favourites: MutableList<FavoriteItem>) {
    val remove = { fav:FavoriteItem ->
        favourites.remove(fav)
    }
    Row (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFECF4D6))
    ) {
        LazyColumn (
            content = {
                items(favourites.size) {
                    CatItem(favourites[it], remove)
                }
            }
        )
    }
}

@Composable
fun CatItem(favoriteItem: FavoriteItem, remove: (FavoriteItem) -> (Boolean)) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(
                color = Color(0xFFB5D8CF),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        IconButton(
            onClick = {
                remove(favoriteItem)
            },
            modifier = Modifier
                .align(Alignment.End).size(50.dp)
        ) {
            Icon(
                Icons.Rounded.Close,
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        }
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(start = 10.dp, end = 10.dp),
            model = favoriteItem.imageUrl,
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .padding(10.dp),
            text = favoriteItem.fact,
            fontSize = 20.sp
        )
    }
}
