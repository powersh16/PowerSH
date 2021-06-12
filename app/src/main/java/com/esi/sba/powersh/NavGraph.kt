package com.esi.sba.powersh

import androidx.activity.compose.BackHandler
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.esi.sba.powersh.screens.*
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch


object MainDestinations {


    const val SPLASH_SCREE = "splash_page"
    const val MAIN_PAGE = "main_page"
    const val DETAIL_PAGE = "detail_page"
    const val REGESTRATION_PAGE = "regestration_page"
    const val CART_PAGE = "CART_page"



    const val INFO_PAGE_ONE = "info_page_one"
    const val INFO_PAGE_TWO = "info_page_two"
    const val INFO_PAGE_THREE = "info_page_three"

}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
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

        composable(MainDestinations.DETAIL_PAGE) {

            BackHandler {
                navController.navigate(MainDestinations.MAIN_PAGE)
            }

            //   detailScreen(navController, isDetailScreenVisible)
        }







        composable(MainDestinations.CART_PAGE) {

            BackHandler {
                navController.navigate(MainDestinations.MAIN_PAGE)
            }

            cartScreen(navController = navController)
        }




        composable(MainDestinations.MAIN_PAGE) {

            val pageState = remember { mutableStateOf("HOME") }

            val scaffoldState = rememberBottomSheetScaffoldState(
                drawerState = rememberDrawerState(DrawerValue.Closed),
                bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)

            )


            val scope = rememberCoroutineScope()
            BackHandler {


                if (pageState.value.equals("HOME")) {
                    if (scaffoldState.drawerState.isOpen) {
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    } else if (scaffoldState.bottomSheetState.isExpanded) {
                        scope.launch {
                            scaffoldState.bottomSheetState.collapse()
                        }
                    } else finishActivity()
                } else {
                    pageState.value = "HOME"
                }


            }
            mainScreen(
                navController,
                pageState = pageState,
                scaffoldState = scaffoldState
            )

        }

        composable(MainDestinations.REGESTRATION_PAGE) {


            var tabState = remember {
                mutableStateOf(0)
            }

            BackHandler {
                if (tabState.value == 0) {
                    navController.navigate(MainDestinations.MAIN_PAGE)
                } else {
                    tabState.value = 0
                }
            }




            authentificationScreen(tabState = tabState)
        }

        composable(MainDestinations.INFO_PAGE_ONE) {
            BackHandler {
                finishActivity()
            }
            infoScreenOne(navController)
        }

        composable(MainDestinations.INFO_PAGE_TWO) {
            BackHandler {
                navController.navigate(MainDestinations.INFO_PAGE_ONE)
            }
            infoScreenTwo(navController)
        }

        composable(MainDestinations.INFO_PAGE_THREE) {
            BackHandler {
                navController.navigate(MainDestinations.INFO_PAGE_TWO)
            }
            infoScreenThree(navController)
        }


    }
}
