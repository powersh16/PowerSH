package com.esi.sba.powersh

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.compose.rememberNavController
import com.esi.sba.powersh.model.CardItem
import com.esi.sba.powersh.model.DataProvider
import com.esi.sba.powersh.ui.theme.PowerSHTheme
import com.google.accompanist.pager.ExperimentalPagerApi


@ExperimentalPagerApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainApp(finishActivity: () -> Unit) {
    PowerSHTheme {

        val navController = rememberNavController()
        val cartProduct = remember {  mutableStateListOf<CardItem>() }

        Scaffold(
            backgroundColor = MaterialTheme.colors.primarySurface,
        ) { innerPaddingModifier ->
            NavGraph(
                finishActivity = finishActivity,
                navController = navController,
                cartProduct = cartProduct
            )
        }
    }
}


@Preview(showBackground = true)
@Composable

fun DefaultPreview() {
    val navController = rememberNavController()
    PowerSHTheme {
       // CanvaScreen(navController = navController)
    }
}