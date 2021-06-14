package com.esi.sba.powersh.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.esi.sba.powersh.R
import com.esi.sba.powersh.cartListProducts
import com.esi.sba.powersh.model.CardItem
import com.esi.sba.powersh.model.DataProvider
import com.esi.sba.powersh.ui.theme.CardCoverPink
import com.esi.sba.powersh.ui.theme.PowerSHRed
import com.esi.sba.powersh.ui.theme.PowerSHTheme

@ExperimentalMaterialApi
@Composable
fun cartScreen(
    navController: NavController,
    cartProduct: MutableList<CardItem>,
){



    val totalPrice = remember {
        mutableStateOf(0)
    }


    totalPrice.value = 0

    for (item in cartProduct) {
        totalPrice.value += item.price * item.quantity
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
            //  .verticalScroll(state = rememberScrollState())
            .background(Color(0xFFFCFDFF))
        ,
    ) {

        if (
            cartProduct.size == 0
        ){
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 32.dp, end = 32.dp),
                painter = painterResource(id = R.drawable.ic_empty_cart),
                contentDescription = "Add To Cart"
            )
            Text(
                color = Color.DarkGray,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                text = "Cart is empty",
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }else {
            cartListProducts(
                navController = navController,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                cartProduct = cartProduct
            ) {
                cartProduct.removeAt(it)
                Log.d("akramakramakram", " cartProduct.removeAt(it) $it")

            }
        }


        Spacer(modifier = Modifier.weight(1f))


       Row(
           modifier = Modifier
               .fillMaxWidth()
               .padding(start = 16.dp, end = 0.dp, bottom = 8.dp, top = 16.dp)
       ) {
           Column(
               modifier = Modifier
           ) {
               Text(
                   color = Color.DarkGray,
                   fontStyle = FontStyle.Normal,
                   fontWeight = FontWeight.SemiBold,
                   fontSize = 14.sp,
                   textAlign = TextAlign.Start,
                   text = "Total Price",
                   modifier = Modifier
                       .align(Alignment.Start)
               )

               Text(
                   color = PowerSHRed,
                   fontStyle = FontStyle.Normal,
                   fontWeight = FontWeight.Bold,
                   textAlign = TextAlign.Start,
                   fontSize = 18.sp,
                   text = "${totalPrice.value} DA",
                   modifier = Modifier
                       .align(Alignment.Start)
               )

           }
           Spacer(modifier = Modifier.weight(1f))
           Button(
               enabled = false,
               shape = RoundedCornerShape(14.dp),
               colors = ButtonDefaults.buttonColors(
                   backgroundColor = CardCoverPink,
                   contentColor = CardCoverPink,
                   disabledBackgroundColor = CardCoverPink,
                   disabledContentColor = CardCoverPink,
               ),
               modifier = Modifier
                   .background(color = CardCoverPink, shape = RoundedCornerShape(14.dp))
                   .align(Alignment.CenterVertically),
               onClick = {

               }) {
               Text(
                   text = "Empty Cart",
                   color =Color.DarkGray,
                   style = TextStyle(
                       background = CardCoverPink,
                   ),
                   textAlign = TextAlign.Center,
                   modifier = Modifier.padding(
                       start = 24.dp,
                       end = 24.dp,
                       top = 6.dp,
                       bottom = 6.dp
                   )
               )
           }



       }

    }

}



@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun cartPreview() {

    val navController = rememberNavController()
    val cartProduct = remember { DataProvider.cartList }
    PowerSHTheme {
        cartScreen(
            navController =navController,
            cartProduct = cartProduct)
    }
}