package com.esi.sba.powersh.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.esi.sba.powersh.ui.theme.CardCover
import com.esi.sba.powersh.ui.theme.CardCoverPink
import com.esi.sba.powersh.ui.theme.PowerSHRed
import com.esi.sba.powersh.ui.theme.PowerSHTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset

data class LoginState(var state: Screen = Screen.LOGIN) {

    enum class Screen(
        val title: String = "Tab"
    ) {
        LOGIN(title = "Login"),
        SIGN_UP(title = "Sign Up"),
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun loginTabs(
    screenState: LoginState,
    modifier: Modifier,
    pagerState: PagerState,
    onPageSelected : (Int) -> Unit
) {
    val (selectedTab, setSelectedTab) = remember {
        mutableStateOf(
            LoginState.Screen.values().indexOf(screenState.state)
        )
    }

    val tabs = LoginState.Screen.values()

    TabRow(
        modifier= modifier,
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        },
        backgroundColor =PowerSHRed,
        contentColor = CardCoverPink,
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
                            color = if (pagerState.currentPage == index) Color.White  else CardCoverPink,
                        )
                    },
                    selected = index == pagerState.currentPage,
                    selectedContentColor = Color.White,
                    onClick = {
                     //   pagerState.currentPage = index
                        onPageSelected(index)
                    }
                )
            }
        }
    )
}

/*

@Preview
@Composable
fun tabPreview() {


    var tabState = remember {
        mutableStateOf(0)
    }

    PowerSHTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {


            loginTabs(
                screenState = LoginState(),
                modifier = Modifier.align(Alignment.BottomCenter),
                selectedScreen =tabState,
            )
        }
    }
}*/
