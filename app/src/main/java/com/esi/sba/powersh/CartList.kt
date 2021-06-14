package com.esi.sba.powersh

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.esi.sba.powersh.model.CardItem
import com.esi.sba.powersh.model.DataProvider
import com.esi.sba.powersh.ui.theme.PowerSHRed
import com.esi.sba.powersh.ui.theme.PowerSHTheme


@ExperimentalMaterialApi
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun cartListProducts(
    navController: NavController,
    modifier: Modifier = Modifier,
    cartProduct: MutableList<CardItem>,
    onRemoveItem: (Int) -> Unit

    ) {


    LazyColumn (
        modifier = Modifier.padding(start = 0.dp, end = 0.dp),
        contentPadding = PaddingValues(
            top = 2.dp,
            bottom = 2.dp
            )
    ){

        itemsIndexed(cartProduct) { index, row ->
            cardItem(row){
                onRemoveItem(index)
            }
            Spacer(modifier = Modifier.padding(4.dp))

        }
    }


}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun cardItem(
    item: CardItem,
    onRemove : () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    Card(shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(0.dp)
            .clickable {
                Log.d("cardproductItemClick", "Clicked")

            }
    ) {


            Row(modifier = Modifier.fillMaxWidth()
            ){

                Box( )
                {
                    Image(
                        painter = painterResource(
                            id = item.ImageId
                        ),
                        contentDescription = "Shoes",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .padding(0.dp)
                            .width(96.dp)
                            .height(96.dp)
                            .align(Center)
                            .background(
                                shape = RoundedCornerShape(8.dp),
                                color = Color.LightGray.copy(alpha = 0.1f)
                            )
                    )

                    Column(
                        Modifier
                            .padding(0.dp)
                            .width(96.dp)
                            .height(96.dp)
                            .align(Center)
                            .background(
                                shape = RoundedCornerShape(8.dp),
                                color = Color.LightGray.copy(alpha = 0.1f)
                            )
                    ) {}

                }


                Text(
                    text ="x${item.quantity}",
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp)
                        .align(CenterVertically)
                )

                Column(
                    modifier = Modifier
                        .padding(start = 4.dp, end = 4.dp)
                        .align(CenterVertically)
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Text(
                        text = item.title,
                        color = Color.DarkGray,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(start = 4.dp, end = 4.dp)
                    )
                    Text(
                        text ="Color: ${item.color}, Size: ${item.size}",
                        color = Color.Gray,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(start = 4.dp, end = 4.dp)
                    )
                    Text(
                        text = item.price.toString(),
                        color = PowerSHRed,
                        modifier = Modifier.padding(start = 4.dp, end = 4.dp)
                    )


                }


                IconButton(
                    onClick = {
                    Log.d("akramakramakram","IconButton clicked")
                        onRemove()
                },

                    modifier = Modifier
                        .background(shape = CircleShape, color = Color.Transparent)
                        .align(CenterVertically)
                    ) {
                   Icon(
                       imageVector = Icons.Outlined.DeleteForever,
                       contentDescription = "Remove item",
                        tint =  Color.LightGray,
                        modifier = Modifier
                            .size(24.dp)
                      // .padding(start = 24.dp, end = 24.dp)
                       .align(CenterVertically)
                   )
                }



            }


        }


}















@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun cardProductPreview() {
    val cartProduct = remember { mutableListOf<CardItem>() }
    cartProduct.addAll( DataProvider.cartList)
    val navController: NavHostController = rememberNavController()
    PowerSHTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            cartListProducts(
                navController,
                Modifier,
                cartProduct
            ){
                // nothing
            }


        }


    }

}



