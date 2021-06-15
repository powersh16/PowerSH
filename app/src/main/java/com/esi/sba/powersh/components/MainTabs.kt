package com.esi.sba.powersh.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.esi.sba.powersh.ui.theme.PowerSHRed
import com.esi.sba.powersh.ui.theme.PowerSHTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState

data class ScreenState(var state: Screen = Screen.TOUS) {

    enum class Screen(
        val title: String = "Tab"
    ) {
        TOUS(title = "Tous"),
        HOMME(title = "Homme"),
        FEMME(title = "Femme"),
        ENFANT(title = "Enfant")
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsPanel(
    pagerState: PagerState,
    onTabSelected : (Int) -> Unit = {},
    //  onNavigate: (ScreenState.Screen) -> Unit,
) {


    val tabs = ScreenState.Screen.values()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        },
        backgroundColor = Color.White,
        contentColor = PowerSHRed,
        divider = {
            TabRowDefaults.Divider(
                color = Color.White
            )
        },
        tabs = {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    text = {
                        Text(
                            text = tab.title,
                            color = if (pagerState.currentPage == index) PowerSHRed else   Color.Gray,
                        )
                           },
                    selected = index == pagerState.currentPage,
                    selectedContentColor = PowerSHRed,
                    onClick = {
                     onTabSelected(index)
                    }
                )
            }
        }
    )
}


@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun splashPreview() {

    val pagerState = rememberPagerState(pageCount = 4)

    PowerSHTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {


            TabsPanel(
                pagerState = pagerState,
            )
        }
    }
}