package com.esi.sba.powersh.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.esi.sba.powersh.MainDestinations.MAIN_PAGE
import com.esi.sba.powersh.R
import com.esi.sba.powersh.ui.theme.PowerSHRed
import com.esi.sba.powersh.ui.theme.PowerSHTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun splashScreen(navController: NavController) {




    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = PowerSHRed
            )
    ) {

        Image(

            modifier = Modifier.align(Center),
            painter = painterResource(id = R.drawable.powersh_without_background),
            contentDescription = "PowerSH icon"
        )

        Text(
            color = Color.White,
            fontStyle = FontStyle.Italic,
            text = "Power by PICOS",
            modifier = Modifier
                .padding(16.dp)
                .align(BottomCenter)
        )

    }



    GlobalScope.launch(Dispatchers.Main) {
        delay(5000)
        navController.popBackStack()
        navController.navigate(MAIN_PAGE)
    }

}


@Preview
@Composable
fun splashPreview() {

    val navController = rememberNavController()
    PowerSHTheme {
        splashScreen(navController)
    }
}