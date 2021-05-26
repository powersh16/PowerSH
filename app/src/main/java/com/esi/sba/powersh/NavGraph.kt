package com.esi.sba.powersh

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.esi.sba.powersh.screens.mainScreen
import com.esi.sba.powersh.screens.splashScreen


object MainDestinations {


    const val SPLASH_SCREE = "splash_page"
    const val MAIN_PAGE = "main_page"

}

@Composable
fun NavGraph(
    finishActivity: () -> Unit = {},
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.SPLASH_SCREE,
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(MainDestinations.SPLASH_SCREE) {
            BackHandler {
                finishActivity()
            }

            splashScreen(navController)

        }
        composable(MainDestinations.MAIN_PAGE) {
            BackHandler {
                finishActivity()
            }

            mainScreen(navController)


        }

    }
}

