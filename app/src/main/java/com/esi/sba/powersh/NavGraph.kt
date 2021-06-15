package com.esi.sba.powersh

import androidx.activity.compose.BackHandler
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.esi.sba.powersh.components.indicator.OnboardingScreen
import com.esi.sba.powersh.model.CardItem
import com.esi.sba.powersh.screens.*
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch


object MainDestinations {


    const val SPLASH_SCREE = "splash_page"
    const val MAIN_PAGE = "main_page"
    const val DETAIL_PAGE = "detail_page"
    const val REGESTRATION_PAGE = "regestration_page"
    const val CART_PAGE = "CART_page"


    const val CHECKOUT_PAGE = "checkout_page"


    const val ONBOARDING = "onboarding"

}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NavGraph(
    finishActivity: () -> Unit = {},
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.CHECKOUT_PAGE,
    cartProduct: SnapshotStateList<CardItem>,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {







        composable(MainDestinations.ONBOARDING) {
            OnboardingScreen(context = LocalContext.current) {
                navController.navigate(route = MainDestinations.MAIN_PAGE)
            }
        }


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

            cartScreen(navController = navController, cartProduct = cartProduct )
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
                scaffoldState = scaffoldState,
                cartProduct = cartProduct
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




            authentificationScreen( navController = navController, tabState = tabState)
        }


        composable(MainDestinations.CHECKOUT_PAGE) {
            BackHandler {
                navController.navigate(MainDestinations.CART_PAGE)
            }
            checkoutScreen(navController, cartProduct)
        }




    }
}
