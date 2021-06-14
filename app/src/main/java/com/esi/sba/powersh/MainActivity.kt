package com.esi.sba.powersh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import com.esi.sba.powersh.screens.newSlashScreen
import com.esi.sba.powersh.ui.theme.PowerSHRed
import com.esi.sba.powersh.ui.theme.PowerSHTheme
import com.esi.sba.powersh.ui.theme.RedDark
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        window.statusBarColor = PowerSHRed.toArgb()


        setContent {
            MainApp { finish() }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun splashPreview1(){
    MainApp {  }
}


