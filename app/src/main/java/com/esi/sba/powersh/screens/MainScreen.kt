package com.esi.sba.powersh.screens

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.esi.sba.powersh.components.*
import com.esi.sba.powersh.ui.theme.CardCover
import com.esi.sba.powersh.ui.theme.PowerSHTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun mainScreen(
    navController: NavController,
    pageState: MutableState<String>,
    scaffoldState: BottomSheetScaffoldState
) {
    val valueofSearshbar = remember {
        mutableStateOf("")
    }




    val isDetailScreenVisible = remember {
    mutableStateOf(false)
}


    //  val selectedItem = remember { mutableStateOf("HOME")}




    val scope = rememberCoroutineScope()



    BottomSheetScaffold(
            scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        ),
            modifier = Modifier.fillMaxSize(),
            topBar = {
                mainTopBar(
                    onOpenMenu = {
                        scope.launch {
                            if (scaffoldState.drawerState.isOpen)
                                scaffoldState.drawerState.close()
                            else scaffoldState.drawerState.open()
                        }
                        Log.d("DrawerDrawing", "onOpenMenu")

                    }
                )
            },
        sheetContent = {

            detailScreen(navController, isDetailScreenVisible, bottomSheetStat = scaffoldState, pageState = pageState)

        },
            drawerContent = {

                mainDrawer(
                    navController = navController,
                    scope = scope,
                    selectedScreen = pageState,
                    scaffoldState = scaffoldState
                )

            },
        ) {

            if (
                pageState.value.equals("HOME")
            ) {
                mainScreen(
                    navController = navController,
                    valueofSearshbar = valueofSearshbar,
                    bottomSheetStat = scaffoldState
                )
            } else if (
                pageState.value.equals("CART")
            ) {
                cartScreen(navController = navController)
            } else {

                Text(
                    text = pageState.value,
                    color = Color.Black
                )


            }


        }
}




@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun mainScreen(
    valueofSearshbar: MutableState<String>,
    navController: NavController,
    bottomSheetStat: BottomSheetScaffoldState,
    ){


    val coroutineScope = rememberCoroutineScope()



   // isDetailScreenVisible.value = !bottomSheetStat.bottomSheetState.isCollapsed


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
         listProducts(navController = navController ,modifier = Modifier.align(CenterHorizontally), bottomSheetStat = bottomSheetStat)

     }

}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun MainPreview() {

    val scaffoldState = rememberBottomSheetScaffoldState(
        drawerState= rememberDrawerState(DrawerValue.Closed),
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)

    )
    val navController = rememberNavController()
    val pageState = remember { mutableStateOf("HOME") }
    PowerSHTheme {
        mainScreen(navController, pageState, scaffoldState)
    }


}