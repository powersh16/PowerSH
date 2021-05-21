package com.esi.sba.powersh.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.esi.sba.powersh.ui.theme.PowerSHTheme

@Composable
fun mainTopBar(
    onOpenMenu:  () -> Unit ={},
) {
    TopAppBar(
        title = {
            Text(
                color = Color.Black,
                text = "PowerSH"
            )
        },
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            IconButton(onClick = { onOpenMenu() }) {
                Icon(
                    tint = Color.Black,
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Open Menu"
                )
            }
        },
        actions = {},
        elevation = 0.dp,
    )
}


@Preview
@Composable
fun topBarPreview() {

    PowerSHTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()

        ) {
            mainTopBar()

        }


    }

}