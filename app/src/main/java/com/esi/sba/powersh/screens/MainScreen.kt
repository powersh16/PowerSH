package com.esi.sba.powersh.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.esi.sba.powersh.components.RoundedSearchBar
import com.esi.sba.powersh.components.mainTopBar
import com.esi.sba.powersh.ui.theme.PowerSHRed
import com.esi.sba.powersh.ui.theme.PowerSHTheme


@Composable
fun mainScreen(navController: NavController) {
    val valueofSearshbar by remember {
        mutableStateOf("")
    }
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            mainTopBar()
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                RoundedSearchBar(
                    modifier = Modifier
                        .weight(1f),
                    value = valueofSearshbar,
                    label = "Search"
                )
                IconButton(
                    modifier = Modifier.align(CenterVertically)
                        .padding(start = 8.dp)
                        .background(PowerSHRed,shape = RoundedCornerShape(8.dp)),

                    onClick = {}
                ) {
                    Icon(
                        modifier = Modifier.align(CenterVertically),
                        tint = Color.White,
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Stings button"
                    )
                }
            }



        }

    }

}

@Preview
@Composable
fun MainPreview() {

    val navController = rememberNavController()
    PowerSHTheme {
        mainScreen(navController)
    }


}