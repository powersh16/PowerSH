package com.esi.sba.powersh.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.esi.sba.powersh.model.DataProvider
import com.esi.sba.powersh.model.Product
import com.esi.sba.powersh.ui.theme.CardCoverPink
import com.esi.sba.powersh.ui.theme.MainCardList
import com.esi.sba.powersh.ui.theme.PowerSHRed
import com.esi.sba.powersh.ui.theme.PowerSHTheme
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun listProducts(
    navController: NavController,
    modifier: Modifier,
    bottomSheetStat: BottomSheetScaffoldState
) {


    val products = remember { DataProvider.productList }


    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        contentPadding = PaddingValues(
            top = 8.dp,
            bottom = 8.dp
        )
    ) {
        itemsIndexed(products) { row, item ->
            productItem(navController, item, bottomSheetStat = bottomSheetStat)
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


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun productItem(
    navController: NavController,
    product: Product,
    bottomSheetStat: BottomSheetScaffoldState
) {
    val coroutineScope = rememberCoroutineScope()

    Card(shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                Log.d("productItemClick", "Clicked")
                //   navController.navigate(MainDestinations.DETAIL_PAGE)

                coroutineScope.launch {
                    bottomSheetStat.bottomSheetState.expand()
                }

            }
    ) {

        Column(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .clickable {
                    Log.d("productItemClick", "Clicked2")
                  //  navController.navigate(MainDestinations.DETAIL_PAGE)
                    coroutineScope.launch {
                        bottomSheetStat.bottomSheetState.expand()
                    }

                }
            ,

            ) {









            Box(modifier = Modifier.fillMaxWidth()
            ){

                Image(
                    painter = painterResource(
                        id = product.puppyImageId
                    ),
                    contentDescription = "Shoes",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .padding(1.dp)
                        .fillMaxWidth()
                        .height(160.dp)
                        .align(Center)
                )

                Column(
                    Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .align(Center)
                        .background(
                            color = CardCoverPink.copy(alpha = 0.1f)
                        )
                ) {}

            }













            Text(
                text = product.title,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
            Text(
                text = product.price,
                color = PowerSHRed,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }


    }


}
















@ExperimentalMaterialApi
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun detailProductImagesList(
    modifier: Modifier = Modifier,
) {

    val products = remember { DataProvider.productList }

    LazyRow (
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
        ,
    ){

        itemsIndexed(products) { index, row ->

                Image(
                    painter = painterResource(
                        id = row.puppyImageId
                    ),
                    contentDescription = "Shoes Detail",
                    contentScale = ContentScale.FillBounds,
                    modifier = modifier
                        .height(260.dp)
                      //  .width(400.dp)
                )

            Column(
                modifier
                    .padding(end = 0.dp)
                    .height(260.dp)
                    .background(
                        color = Color.LightGray.copy(alpha = 0.1f)
                    )
            ) {}


            }

    }



}

















@Preview
@Composable
fun productPreview() {

    val navController: NavHostController = rememberNavController()
    val isDetailScreenVisible = remember {
        mutableStateOf(false)
    }
    PowerSHTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

         /*   listProducts(
                navController,
                Modifier.align(Alignment.Center),
                bottomSheetStat = bottomSheetStat
            )
*/
        }


    }

}



