package com.esi.sba.powersh.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.esi.sba.powersh.MainDestinations
import com.esi.sba.powersh.R
import com.esi.sba.powersh.ui.theme.PowerSHRed
import com.esi.sba.powersh.ui.theme.PowerSHTheme


@Composable
fun infoScreenOne(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
            .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Spacer(modifier = Modifier.weight(2f))
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 32.dp, end = 32.dp)
            ,
            painter = painterResource(id = R.drawable.ic_shopping),
            contentDescription = "Add a Product"
        )
        Spacer(modifier = Modifier.weight(1f))

            Text(
                color = Color.Black,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                text = "Choose a Product",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                color = Color.Gray,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center,
                text = "Choose any product that you like from out store",
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            FloatingActionButton(
                contentColor =  PowerSHRed,
                backgroundColor = PowerSHRed,
                shape = CircleShape,
                modifier = Modifier
                    .padding(start = 0.dp,end=2.dp)
                    .align(CenterVertically)
                    .height(8.dp)
                    .width(16.dp),
                onClick = { /*TODO*/ }) {
                Spacer(modifier = Modifier.size(4.dp))
            }
            FloatingActionButton(
                contentColor =  PowerSHRed,
                backgroundColor = PowerSHRed,
                shape = CircleShape,
                modifier = Modifier
                    .padding(start = 2.dp,end=2.dp)
                    .align(CenterVertically)
                    .size(8.dp),
                onClick = { /*TODO*/ }) {

            }
            FloatingActionButton(
                contentColor =  PowerSHRed,
                backgroundColor = PowerSHRed,
                shape = CircleShape,
                modifier = Modifier
                    .padding(start = 2.dp,end=2.dp)
                    .align(CenterVertically)
                    .size(8.dp),
                onClick = { /*TODO*/ }) {

            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = PowerSHRed,
                ),
                shape = CircleShape,
                modifier= Modifier.background(color = PowerSHRed, shape = CircleShape),
                onClick = {
                    navController.navigate(MainDestinations.INFO_PAGE_TWO)
            }) {
               Text(
                   text = "Next",
                   textAlign = TextAlign.Center,
                   color = Color.White,
                   modifier = Modifier.padding(start = 16.dp, end = 16.dp)
               )
            }
        }

    }

}














@Composable
fun infoScreenTwo(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
            .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Spacer(modifier = Modifier.weight(2f))
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 32.dp, end = 32.dp)
            ,
            painter = painterResource(id = R.drawable.ic_add_cart),
            contentDescription = "Add To Cart"
        )
        Spacer(modifier = Modifier.weight(1f))

        Text(
            color = Color.Black,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            text = "Choose a Product",
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            color = Color.Gray,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            text = "Add the product to your shopping cart",
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            FloatingActionButton(
                contentColor =  PowerSHRed,
                backgroundColor = PowerSHRed,
                shape = CircleShape,
                modifier = Modifier
                    .padding(start = 2.dp,end=2.dp)
                    .align(CenterVertically)
                    .size(8.dp),
                onClick = { /*TODO*/ }) {

            }
            FloatingActionButton(
                contentColor =  PowerSHRed,
                backgroundColor = PowerSHRed,
                shape = CircleShape,
                modifier = Modifier
                    .padding(start = 0.dp,end=2.dp)
                    .align(CenterVertically)
                    .height(8.dp)
                    .width(16.dp),
                onClick = { /*TODO*/ }) {
                Spacer(modifier = Modifier.size(4.dp))
            }
            FloatingActionButton(
                contentColor =  PowerSHRed,
                backgroundColor = PowerSHRed,
                shape = CircleShape,
                modifier = Modifier
                    .padding(start = 2.dp,end=2.dp)
                    .align(CenterVertically)
                    .size(8.dp),
                onClick = { /*TODO*/ }) {

            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = PowerSHRed,
                ),
                shape = CircleShape,
                modifier= Modifier.background(color = PowerSHRed, shape = CircleShape),
                onClick = {
                    navController.navigate(MainDestinations.INFO_PAGE_THREE)

                }) {
                Text(
                    text = "Next",
                    color = Color.White,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                )
            }
        }

    }

}



@Composable
fun infoScreenThree(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
            .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Spacer(modifier = Modifier.weight(2f))
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 32.dp, end = 32.dp)
            ,
            painter = painterResource(id = R.drawable.ic_order_confirm),
            contentDescription = "Add To Cart"
        )
        Spacer(modifier = Modifier.weight(1f))

        Text(
            color = Color.Black,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            text = "Confirm Order",
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            color = Color.Gray,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            text = "Fill your dilevery information and wait for your product at home",
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            FloatingActionButton(
                contentColor =  PowerSHRed,
                backgroundColor = PowerSHRed,
                shape = CircleShape,
                modifier = Modifier
                    .padding(start = 2.dp,end=2.dp)
                    .align(CenterVertically)
                    .size(8.dp),
                onClick = { /*TODO*/ }) {

            }

            FloatingActionButton(
                contentColor =  PowerSHRed,
                backgroundColor = PowerSHRed,
                shape = CircleShape,
                modifier = Modifier
                    .padding(start = 2.dp,end=2.dp)
                    .align(CenterVertically)
                    .size(8.dp),
                onClick = { /*TODO*/ }) {

            }
            FloatingActionButton(
                contentColor =  PowerSHRed,
                backgroundColor = PowerSHRed,
                shape = CircleShape,
                modifier = Modifier
                    .padding(start = 0.dp,end=2.dp)
                    .align(CenterVertically)
                    .height(8.dp)
                    .width(16.dp),
                onClick = { /*TODO*/ }) {
                Spacer(modifier = Modifier.size(4.dp))
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = PowerSHRed,
                ),
                shape = CircleShape,
                modifier= Modifier.background(color = PowerSHRed, shape = CircleShape),
                onClick = {
                    navController.navigate(MainDestinations.MAIN_PAGE)
                }) {
                Text(
                    text = "Get Started",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(start = 4.dp, end = 4.dp)
                )
            }
        }

    }

}






@Preview
@Composable
fun infoScreenPreview() {

    val navController = rememberNavController()
    PowerSHTheme {
        infoScreenThree(navController)
    }
}