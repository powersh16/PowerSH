package com.esi.sba.powersh.screens

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.esi.sba.powersh.components.*
import com.esi.sba.powersh.components.extensions.IndicatorState
import com.esi.sba.powersh.model.CardItem
import com.esi.sba.powersh.model.DataProvider
import com.esi.sba.powersh.ui.theme.PowerSHRed
import com.esi.sba.powersh.ui.theme.PowerSHTheme
import com.esi.sba.powersh.ui.theme.YellowOnboarding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun mainScreen(
    navController: NavController,
    pageState: MutableState<String>,
    scaffoldState: BottomSheetScaffoldState,
    cartProduct: SnapshotStateList<CardItem>
) {
    val valueofSearshbar = remember {
        mutableStateOf("")
    }


    val isDetailScreenVisible = remember {
    mutableStateOf(false)
}


    val restoreValues = remember {
        mutableStateOf(false)
    }


    val selectedProduct = remember {
        mutableStateOf(0)
    }


    //  val selectedItem = remember { mutableStateOf("HOME")}


  //  val cartProduct = remember { DataProvider.cartList }


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

            detailScreen(
                navController,
                isDetailScreenVisible,
                bottomSheetStat = scaffoldState,
                pageState = pageState,
                cartProduct = cartProduct,
                selectedProduct = selectedProduct,
                restoreValues = restoreValues
            )

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
                    bottomSheetStat = scaffoldState,
                    selectedProduct = selectedProduct,
                    restoreValues = restoreValues
                )
            } else if (
                pageState.value.equals("CART")
            ) {
                cartScreen(
                    navController = navController,
                    cartProduct= cartProduct
                    )
            } else {
                Text(
                    text = pageState.value,
                    color = Color.Black
                )

            }
        }
}




@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun mainScreen(
    valueofSearshbar: MutableState<String>,
    navController: NavController,
    bottomSheetStat: BottomSheetScaffoldState,
    selectedProduct: MutableState<Int>,
    restoreValues: MutableState<Boolean>,
    ){


    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = 4)

     Column(
         modifier = Modifier
             .fillMaxSize()
             .background(YellowOnboarding.copy(alpha = 0.05f))

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

         TabsPanel(
             pagerState = pagerState
         ){
             coroutineScope.launch {
                 pagerState.scrollToPage(it, 0f)
             }
         }

         HorizontalPager(state = pagerState) { page ->

             when(page){
                 0 -> listProducts(navController = navController ,
                             modifier = Modifier,
                             bottomSheetStat = bottomSheetStat,
                             selectedProduct =selectedProduct,
                             onProductClicked ={
                                 restoreValues.value = true
                             }
                         )

                 1 ->  Column() {
                     Text(text = "Second", color = Color.Green)
                     listProducts(navController = navController ,
                         modifier = Modifier,
                         bottomSheetStat = bottomSheetStat,
                         selectedProduct =selectedProduct,
                         onProductClicked ={
                             restoreValues.value = true
                         }
                     )
                 }
                 2 -> listProducts(navController = navController ,
                     modifier = Modifier,
                     bottomSheetStat = bottomSheetStat,
                     selectedProduct =selectedProduct,
                     onProductClicked ={
                         restoreValues.value = true
                     }
                 )
                 3 -> listProducts(navController = navController ,
                     modifier = Modifier,
                     bottomSheetStat = bottomSheetStat,
                     selectedProduct =selectedProduct,
                     onProductClicked ={
                         restoreValues.value = true
                     }
                 )
             }


         }


     }

}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun MainPreview() {

    val cartProduct = remember { DataProvider.cartList }

    val scaffoldState = rememberBottomSheetScaffoldState(
        drawerState= rememberDrawerState(DrawerValue.Closed),
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)

    )
    val navController = rememberNavController()
    val pageState = remember { mutableStateOf("HOME") }
    PowerSHTheme {
        mainScreen(navController, pageState, scaffoldState, cartProduct)
    }


}