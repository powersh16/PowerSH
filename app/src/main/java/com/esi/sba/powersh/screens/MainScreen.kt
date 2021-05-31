package com.esi.sba.powersh.screens

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.esi.sba.powersh.components.*
import com.esi.sba.powersh.ui.theme.CardCover
import com.esi.sba.powersh.ui.theme.PowerSHTheme
import kotlinx.coroutines.launch


@ExperimentalComposeUiApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun mainScreen(navController: NavController) {
    val valueofSearshbar = remember {
        mutableStateOf("")
    }


    val scaffoldState = rememberScaffoldState(
        rememberDrawerState(DrawerValue.Closed)
    )

    val selectedItem = remember { mutableStateOf("HOME")}


    val scope = rememberCoroutineScope()


    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            mainTopBar(
                onOpenMenu = {
                    scope.launch {
                        if (scaffoldState.drawerState.isOpen)
                        scaffoldState.drawerState.close()
                        else scaffoldState.drawerState.open()
                    }
                    Log.d("DrawerDrawing","onOpenMenu")

                }
            )
        },

        drawerContent = {

            mainDrawer(
                navController=  navController,
                scope = scope,
                selectedScreen = selectedItem,
                scaffoldState =scaffoldState
                )

        },
    ) {

        if (
            selectedItem.value.equals("HOME")
        ){
            mainScreen(
                valueofSearshbar = valueofSearshbar
            )
        }else if (
            selectedItem.value.equals("CART")
        ) {
            cartScreen(navController = navController)
        }else {

            Text(text = selectedItem.value,
            color = Color.Black
            )


        }



    }

}




@ExperimentalComposeUiApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun mainScreen(
    valueofSearshbar : MutableState<String>
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CardCover)
        ,
    ) {
        RoundedSearchBar(
            modifier = Modifier
                .weight(1f),
            value = valueofSearshbar.value,
            label = "Search",
            onValueChanged = {

            }
        )

        TabsPanel(screenState = ScreenState())
        listProducts(modifier = Modifier.align(CenterHorizontally))

    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun MainPreview() {

    val navController = rememberNavController()
    PowerSHTheme {
        mainScreen(navController)
    }



}