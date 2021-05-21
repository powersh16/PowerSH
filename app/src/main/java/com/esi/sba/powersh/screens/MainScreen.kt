package com.esi.sba.powersh.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.esi.sba.powersh.ui.theme.PowerSHTheme



@Composable
fun mainScreen(navController: NavController){



}

@Preview
@Composable
fun MainPreview() {

    val navController = rememberNavController()
    PowerSHTheme {
        mainScreen(navController)
    }



}