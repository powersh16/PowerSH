package com.esi.sba.powersh.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.esi.sba.powersh.R
import com.esi.sba.powersh.model.DataProvider
import com.esi.sba.powersh.model.product
import com.esi.sba.powersh.ui.theme.CardCover
import com.esi.sba.powersh.ui.theme.PowerSHRed
import com.esi.sba.powersh.ui.theme.PowerSHTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun listProducts(modifier: Modifier){


    val products = remember { DataProvider.productList }


    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier.padding(start = 16.dp, end =16.dp),
        contentPadding =  PaddingValues(
            top = 8.dp,
            bottom = 8.dp
        )
    ) {
        itemsIndexed(products) { row, item->
            productItem(item)
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }


/*

    LazyColumn (
        modifier = Modifier,
        contentPadding = PaddingValues(
            top = 8.dp,
            bottom = 8.dp
            )
    ){

        itemsIndexed(products) { index, row ->
            productItem(row)

        }
    }
*/


}




@Composable
fun productItem(
    product: product
){
    
    Card(shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(8.dp)
    ) {

        Column(
            modifier = Modifier
                .padding(bottom = 16.dp),

        ){
            Image(painter = painterResource(
                id = product.puppyImageId ),
                contentDescription = "Shoes",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(1.dp)
                    .fillMaxWidth()
                    .height(160.dp)

                    .align(CenterHorizontally)
            )
            Text(text = product.title,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                )
            Text(text = product.price,
                color = PowerSHRed,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }


    }
    
    
}


@Preview
@Composable
fun productPreview() {

    PowerSHTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {

            listProducts(Modifier.align(Alignment.Center))

        }


    }

}



