package com.example.chooseyourhero.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun HeroDescriptionScreen(heroId: Int?, navController: NavController) {
    Box(
        Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = stringResource(id = Heroes.sample[heroId!!].imageUrl),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )

        Box(
            Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            Column(
                Modifier
                    .padding(15.dp)
            ) {
                Text(
                    text = stringResource(id = Heroes.sample[heroId].name),
                    color = Color.White,
                    fontSize = 35.sp,
                    style = MaterialTheme.typography.titleMedium,
                )

                Text(
                    modifier = Modifier.padding(vertical = 5.dp),
                    text = stringResource(id = Heroes.sample[heroId].description),
                    color = Color.White,
                    fontSize = 35.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 40.sp
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopStart,
        ) {
            Image(
                modifier = Modifier
                    .clickable { navController.navigate("mainScreen") }
                    .size(100.dp)
                    .padding(20.dp),
                painter = painterResource(id = R.drawable.backward_arrow),
                contentDescription = null
            )
        }
    }
}
