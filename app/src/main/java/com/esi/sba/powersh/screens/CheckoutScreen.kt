package com.esi.sba.powersh.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Money
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.esi.sba.powersh.MainDestinations
import com.esi.sba.powersh.model.CardItem
import com.esi.sba.powersh.model.DataProvider
import com.esi.sba.powersh.model.Step
import com.esi.sba.powersh.ui.theme.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun checkoutScreen(
    navController: NavController,
    cartProduct: MutableList<CardItem>,
) {

    val step = remember {
        mutableStateOf(0)
    }
    var selected = remember { mutableStateOf(MainPayOptions.CASH_OPTION) }


    Scaffold(
        backgroundColor = Color.White,
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 16.dp)
            ) {

                AnimatedVisibility(
                    visible = step.value > 0,
                    enter = slideInHorizontally(),
                    exit = slideOutHorizontally(
                        targetOffsetX = {-it},
                        spring()
                    ),
                ) {
                    OutlinedButton(
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = PowerSHRed),
                        modifier = Modifier
                            //     .background(color = PowerSHRed, shape = RoundedCornerShape(14.dp))
                            .align(Alignment.CenterVertically),
                        onClick = {
                            if (step.value > 0)
                                step.value -= 1
                        }) {
                        Text(
                            text = "Back",
                            color = PowerSHRed,
                            style = TextStyle(
                                background = Color.White,
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 2.dp,
                                bottom = 2.dp
                            )
                        )
                    }


                }

                Spacer(
                    modifier =
                    Modifier
                        .weight(1f)
                )



                Button(
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = PowerSHRed,
                        contentColor = Color.White,
                        disabledBackgroundColor = CardCoverPink,
                        disabledContentColor = CardCoverPink,
                    ),
                    modifier = Modifier
                        .background(color = CardCoverPink, shape = RoundedCornerShape(14.dp))
                        .align(Alignment.CenterVertically),
                    onClick = {
                        if (step.value < 2)
                            step.value += 1
                    }) {
                    Text(
                        text = if (step.value < 2) "Next" else "Confirm",
                        color = YellowOnboarding,
                        style = TextStyle(
                            background = PowerSHRed,
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 2.dp,
                            bottom = 2.dp
                        )
                    )
                }


            }


        },
        topBar = {

            Column() {
                TopAppBar(
                    title = {
                        Text(
                            color = Color.Black,
                            text = "Checkout"
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(MainDestinations.CART_PAGE)
                        }) {
                            Icon(
                                tint = Color.Black,
                                imageVector = Icons.Outlined.ArrowBack,
                                contentDescription = "Back to Cart Screen"
                            )
                        }
                    },
                    actions = {},
                    elevation = 0.dp,
                )
                stepsWizard(step)
            }

        }
    ) {


        firstStep(
            step.value == 0,
            selected = selected
        )
        secondStep(
            visible = step.value == 1,
            selected = selected
        )


    }


}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun firstStep(visible: Boolean, selected: MutableState<String>) {

    val scrollState = rememberScrollState()


    AnimatedVisibility(
        visible = visible,
        modifier = Modifier
            .fillMaxSize()
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
           //     .verticalScroll(scrollState)
        ) {
            displayRadioGroup(selected = selected)
            cashOption(visible = selected.value == MainPayOptions.CASH_OPTION , price = 7000, Modifier.weight(1f))
            ccpOption(visible = selected.value == MainPayOptions.CCP_OPTION , price = 6000, Modifier.weight(1f))
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun secondStep(visible: Boolean, selected: MutableState<String>) {

    AnimatedVisibility(
        visible = visible,
        modifier = Modifier.fillMaxSize()
    )
    {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            displayRadioGroup(selected = selected)
            cashOption(visible = true, price = 7000, Modifier.weight(1f))

        }
    }
}





@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ccpOption(visible: Boolean, price: Int, modifier: Modifier) {

    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(),
        exit = slideOutHorizontally(
            targetOffsetX ={ it },
            animationSpec=  spring()
        ),
        modifier = Modifier.fillMaxSize()
    )
    {

        Box(modifier =
        modifier
            .fillMaxSize()
            .padding(top = 0.dp, bottom = 64.dp, start = 16.dp, end = 16.dp)
            .background(
                Color.Transparent,
                RoundedCornerShape(12.dp),
            )
        ) {

            Card(
                backgroundColor = Color.White,
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp),
                modifier =
                Modifier
                    .fillMaxSize()
                    .align(Center)
            ) {

                Column(
                    Modifier
                        .fillMaxSize()
                ) {


                    Image(
                        painter = painterResource(id = com.esi.sba.powersh.R.drawable.ic_add_cart),
                        contentDescription = "Cash on delivery",
                        modifier = Modifier
                            .padding(16.dp)
                            .size(180.dp)
                            .align(CenterHorizontally)
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f))

                    Text(
                        text = "In the Cash on delivery payment system,\n you have to fill the form and we will send you your purchases within 2 days",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f))

                    Text(
                        text = "the total amount you will pay is : ${price}DA",
                        color = Color.Black,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f))

                    Text(
                        text = "Dear customer, make sure to fill your information carefully and you will be receive a call from our customer service to confirm your order",
                        color = Color.Black,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f))

                }
            }


            Column(
                Modifier
                    .fillMaxSize()
                    .align(Center)
                    .background(
                        color = CardCoverPink.copy(alpha = 0.1f)
                    )
            ) {}


        }

    }
}










@OptIn(ExperimentalAnimationApi::class)
@Composable
fun cashOption(visible: Boolean, price: Int, modifier: Modifier) {

    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(),
        exit = slideOutHorizontally(
            targetOffsetX ={ it },
            animationSpec=  spring()
        ),
        modifier = Modifier.fillMaxSize()
    )
    {

        Box(modifier =
        modifier
            .fillMaxSize()
            .padding(top = 0.dp, bottom = 64.dp, start = 16.dp, end = 16.dp)
            .background(
                Color.Transparent,
                RoundedCornerShape(12.dp),
            )
        ) {

            Card(
                backgroundColor = Color.White,
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp),
                modifier =
                Modifier
                    .fillMaxSize()
                    .align(Center)
            ) {

                Column(
                    Modifier
                        .fillMaxSize()
                ) {


                    Image(
                        painter = painterResource(id = com.esi.sba.powersh.R.drawable.ic_empty_cart),
                        contentDescription = "Cash on delivery",
                        modifier = Modifier
                            .padding(16.dp)
                            .size(180.dp)
                            .align(CenterHorizontally)
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f))

                    Text(
                        text = "In the Cash on delivery payment system,\n you have to fill the form and we will send you your purchases within 2 days",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f))

                    Text(
                        text = "the total amount you will pay is : ${price}DA",
                        color = Color.Black,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f))

                    Text(
                        text = "Dear customer, make sure to fill your information carefully and you will be receive a call from our customer service to confirm your order",
                        color = Color.Black,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f))

                }
            }


            Column(
                Modifier
                    .fillMaxSize()
                    .align(Center)
                    .background(
                        color = CardCoverPink.copy(alpha = 0.1f)
                    )
            ) {}


        }






    }
}

@Composable
fun radioPaymentOption(
    selected: MutableState<String>,
    id: String,
    title: String,
    icon: ImageVector,
    contentDescription: String = "",
    detail: String,
) {
    Row(
        modifier = Modifier
            .padding(8.dp, 8.dp)
            .clickable(onClick = { selected.value = id })
    ) {
        RadioButton(
            selected = selected.value == id,
            onClick = { selected.value = id },
            colors = RadioButtonDefaults.colors(
                selectedColor = PowerSHRed
            ),
            modifier = Modifier
                .padding(start = 0.dp, end = 8.dp)

        )
        Column(
            modifier = Modifier
                .weight(1f)
                .clickable(onClick = { selected.value = id })
        ) {
            Text(
                text = title,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .clickable(onClick = { selected.value = id })
                    .padding(start = 4.dp)
            )
            Text(
                text = detail,
                color = Color.DarkGray,
                fontSize = 12.sp,
                modifier = Modifier
                    .clickable(onClick = { selected.value = id })
                    .padding(start = 4.dp)
            )
        }

        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = if (selected.value == id) PowerSHRed else Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

    }
}


@Composable
fun displayRadioGroup(selected: MutableState<String>) {


       Column(
           Modifier
               .fillMaxWidth()
               .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 8.dp)
               .background(
                   color = YellowOnboarding.copy(alpha = 0.2f)
               )
       ) {
       Card(
           elevation = 2.dp,
           backgroundColor = Color.White,
           shape =  RoundedCornerShape(12.dp),
           modifier =  Modifier
               .align(CenterHorizontally)

       ) {

           Column(
               modifier = Modifier.fillMaxWidth()
           ) {
               radioPaymentOption(
                   selected = selected,
                   id = MainPayOptions.CASH_OPTION,
                   title = "Cash on Delivery",
                   detail = "Pay when the products are delivered to you",
                   contentDescription = "Cash on Delivery icon",
                   icon = Icons.Outlined.Money,
               )

               radioPaymentOption(
                   selected = selected,
                   id = MainPayOptions.CCP_OPTION,
                   title = "CCP or BaridiMob",
                   detail = "Pay now using your CCP or BaridiMob",
                   contentDescription = "CCP or BaridiMob icon",
                   icon = Icons.Outlined.CreditCard,
               )
           }


       }

   }



}


object MainPayOptions {


    const val CASH_OPTION = "chash"
    const val CCP_OPTION = "ccp"


}


@Composable
fun StepItem(
    s: Step,
    index: Int,
    lastIndex: Int,
    selected: Boolean,
    modifier: Modifier
) {

    val color = if (!selected) Color.Gray else PowerSHRed


    val backgroundColor by animateColorAsState(
        color,
        spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
    )


    ConstraintLayout(
        modifier = modifier.padding(top = 8.dp, end = 8.dp)
    ) {
        val (circle, text, leftLine, rightLine) = createRefs()
        Spacer(
            modifier =
            Modifier
                .height(1.dp)
                .fillMaxWidth(0.5f)
                .constrainAs(leftLine) {
                    start.linkTo(parent.start)
                    end.linkTo(circle.start)
                    top.linkTo(circle.top)
                    bottom.linkTo(circle.bottom)
                }
                .background(if (index == 0) Color.Transparent else backgroundColor)
        )

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(0.5f)
                .constrainAs(rightLine) {
                    start.linkTo(circle.end)
                    top.linkTo(circle.top)
                    bottom.linkTo(circle.bottom)
                }
                .background(if (index == lastIndex) Color.Transparent else backgroundColor)
        )


        Box(
            Modifier
                .padding(0.dp)
                .background(color = Color.White)
                .border(1.dp, backgroundColor, CircleShape)
                .constrainAs(circle) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }

        )
        {
            Text(
                text = "",
                modifier =
                Modifier
                    .align(Alignment.Center)
                    .padding(8.dp)
                    .width(16.dp)
                    .height(16.dp)
                    .background(color = backgroundColor, shape = CircleShape)
            )
        }


        Text(
            text = s.title,
            color = backgroundColor,
            fontSize = 14.sp,
            modifier = Modifier
                .constrainAs(text) {
                    start.linkTo(circle.start)
                    end.linkTo(circle.end)
                    top.linkTo(circle.bottom)
                }

        )


    }


}


@Composable
fun stepsWizard(
    step: MutableState<Int>
) {

    val steps = remember { DataProvider.stepList }

    // steps : MutableList<Step>

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp)
    ) {

        steps.forEachIndexed { index, s ->
            StepItem(
                s,
                index,
                steps.size - 1,
                index <= step.value,
                Modifier.weight(1f),
            )
        }
    }

}


@Preview
@Composable
fun checkoutPreview() {

    val navController = rememberNavController()
    val cartProduct = remember { DataProvider.cartList }
    PowerSHTheme {
        checkoutScreen(
            navController = navController,
            cartProduct = cartProduct
        )
    }
}