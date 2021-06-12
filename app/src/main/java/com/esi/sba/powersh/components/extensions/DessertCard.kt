package com.esi.sba.powersh.components.extensions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.esi.sba.powersh.model.Product
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun DessertCard(mproduct: Product) {
    Card(
        Modifier
            .padding(0.dp, 8.dp)
            .width(360.dp)
            .height(240.dp),
        elevation = 3.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Image(
            painter = rememberCoilPainter(
                mproduct.puppyImageId,
                fadeIn = true
            ),
            contentDescription = "Shoes",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.height(200.dp)
        )


    }
}