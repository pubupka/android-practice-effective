package com.example.chooseyourhero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.chooseyourhero.ui.theme.ChooseYourHeroTheme
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chooseyourhero.screens.MainScreen
import com.example.chooseyourhero.screens.HeroDescriptionScreen


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
                        MainScreen (navController = navController)
                    }

                    composable(
                        "heroDescriptionScreen/{heroId}",
                        arguments = listOf(navArgument("heroId") { type = NavType.IntType})
                    ) {
                        stackEntry ->
                        val heroId = stackEntry.arguments?.getInt("heroId")
                        HeroDescriptionScreen(heroId = heroId, navController = navController)
                    }
                }
            }
        }
    }
}
