package com.esi.sba.powersh

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.compose.rememberNavController
import com.esi.sba.powersh.ui.theme.PowerSHTheme


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainApp(finishActivity: () -> Unit) {
    PowerSHTheme {

        val navController = rememberNavController()
        Scaffold(
            backgroundColor = MaterialTheme.colors.primarySurface,
        ) { innerPaddingModifier ->
            NavGraph(
                finishActivity = finishActivity,
                navController = navController,
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