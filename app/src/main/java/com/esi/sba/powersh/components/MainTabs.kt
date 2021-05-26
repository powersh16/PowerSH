package com.esi.sba.powersh.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.esi.sba.powersh.screens.splashScreen
import com.esi.sba.powersh.ui.theme.PowerSHTheme

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

@Composable
fun TabsPanel(
    screenState: ScreenState,
  //  onNavigate: (ScreenState.Screen) -> Unit,
) {
    val (selectedTab, setSelectedTab) = remember {
        mutableStateOf(
            ScreenState.Screen.values().indexOf(screenState.state)
        )
    }

    val tabs = ScreenState.Screen.values()

    TabRow(
        selectedTabIndex = selectedTab,
        backgroundColor = Color.White,
        tabs = {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    text = { Text(text = tab.title, color = Color.Gray) },
                    selected = index == selectedTab,
                    onClick = {
                        setSelectedTab(index)
                     //   onNavigate(tab)
                    }
                )
            }
        }
    )
}


@Preview
@Composable
fun splashPreview() {

    val navController = rememberNavController()
    PowerSHTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {


            TabsPanel(
                screenState = ScreenState(),
                // onNavigate: (ScreenState.Screen) -> Unit,
            )
        }
    }
}