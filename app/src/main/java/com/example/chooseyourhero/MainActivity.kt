package com.example.chooseyourhero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.SnapPositionInLayout
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.chooseyourhero.ui.theme.ChooseYourHeroTheme
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.load


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            ChooseYourHeroTheme {
                NavHost(
                    navController = navController,
                    startDestination = "mainScreen",
                ) {
                    composable("mainScreen")
                    {
                        MainScreen {
                            navController.navigate("heroDescriptionScreen")
                        }
                    }
                    composable("heroDescriptionScreen")
                    {
                        HeroDescriptionScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun HeroDescriptionScreen()
{
    Box (
        Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text("ABOBA", fontSize = 40.sp, style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun MainScreen(onCardClick: () -> Unit) {
    Box (
        Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.background),
                contentScale = ContentScale.Crop
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column (
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
            ShowHeroes(onCardClick)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowHeroes(onCardClick: () -> Unit) {
    val lazyListState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

    val namesAndUrls = listOf(
        Pair("Iron Man", "https://img2.akspic.ru/crops/9/2/9/5/6/165929/165929-figurka-zheleznyj_chelovek-dzhejms_rods-supergeroj-ostov-1125x2436.jpg"),
        Pair("Thor","https://images.wallpapersden.com/image/download/thor-the-dark-world-8k_bGZtaWmUmZqaraWkpJRmZmdqrWdpaGs.jpg"),
        Pair("Venom","https://e0.pxfuel.com/wallpapers/848/316/desktop-wallpaper-best-venom-movie-iphone-ultimate-venom.jpg"),
    )

    LazyRow (
        Modifier.padding(vertical = 20.dp, horizontal = 10.dp),
        state = lazyListState,
        flingBehavior = snapBehavior,
        contentPadding = PaddingValues(horizontal = 30.dp)
    ) {
        items(namesAndUrls) {
            tuple -> ShowHeroCard(heroName = tuple.first, url = tuple.second, onCardClick)
        }
    }
}

@Composable
fun ShowHeroCard(heroName: String, url: String, onClick: () -> Unit) {
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
                onClick()
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomStart,
        ) {
            AsyncImage(
                model = url,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )

            Text(
                modifier = Modifier.padding(15.dp),
                text = heroName,
                color = Color.White,
                fontSize = 35.sp,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun Preview() {
//    ChooseYourHeroTheme {
//        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//            MainScreen(
//
//            )
//        }
//    }
//}