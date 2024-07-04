package com.example.chooseyourhero.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.chooseyourhero.R
import com.example.chooseyourhero.resourses.Heroes

@Composable
fun MainScreen(navController: NavController) {
    Box(
        Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.background),
                contentScale = ContentScale.Crop
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.padding(vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier.size(width = 280.dp, height = 70.dp),
                painter = painterResource(R.drawable.marvel_logo),
                contentDescription = null,
            )
            Text(
                "Choose your hero",
                color = Color.White,
                fontSize = 43.sp,
                style = MaterialTheme.typography.titleMedium
            )
            ShowHeroes(navController)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowHeroes(navController: NavController) {
    val lazyListState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

    val heroesCount = Heroes.sample.size

    LazyRow(
        Modifier.padding(vertical = 20.dp, horizontal = 10.dp),
        state = lazyListState,
        flingBehavior = snapBehavior,
        contentPadding = PaddingValues(horizontal = 30.dp)
    ) {
        items(heroesCount) { heroId ->
            ShowHeroCard(
                heroId = heroId,
                navController
            )
        }
    }
}

@Composable
fun ShowHeroCard(heroId: Int, navController: NavController) {
    Card(
        shape = RoundedCornerShape(
            corner = CornerSize(15.dp)
        ),
        border = BorderStroke(
            width = 2.dp,
            color = Color.Black,
        ),
        modifier = Modifier
            .size(width = 310.dp, height = 630.dp)
            .padding(horizontal = 8.dp)
            .clickable {
                navController.navigate("heroDescriptionScreen/$heroId")
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomStart,
        ) {
            AsyncImage(
                model = stringResource(id = Heroes.sample[heroId].imageUrl),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )

            Text(
                modifier = Modifier.padding(15.dp),
                text = stringResource(id = Heroes.sample[heroId].name),
                color = Color.White,
                fontSize = 35.sp,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}
