package com.esi.sba.powersh.screens

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.esi.sba.powersh.MainDestinations
import com.esi.sba.powersh.components.*
import com.esi.sba.powersh.model.DataProvider
import com.esi.sba.powersh.ui.theme.CardCoverPink
import com.esi.sba.powersh.ui.theme.PowerSHRed
import com.esi.sba.powersh.ui.theme.PowerSHTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@ExperimentalMaterialApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun detailScreen(
    navController: NavController,
    isDetailScreenVisible: MutableState<Boolean>,
    bottomSheetStat: BottomSheetScaffoldState,
    pageState: MutableState<String>
){

  val favourite = remember {
      mutableStateOf(false)
  }

    val backArrow = remember {
        mutableStateOf(false)
    }

    val quantity = remember {
        mutableStateOf(1)
    }


    val isColorDialogVisible = remember {
        mutableStateOf(false)
    }

    val isAddedToCart = remember {
        mutableStateOf(false)
    }


    val colorSelected = remember {
        mutableStateOf("Black")
    }


    val isSizeDialogVisible = remember {
        mutableStateOf(false)
    }
    val sizeSelected = remember {
        mutableStateOf("40")
    }

    val widthImage = remember {
        mutableStateOf(400.dp)
    }


    val products = remember { DataProvider.productList }



    //  val coroutineScope = rememberCoroutineScope()
    AnimatedVisibility(
        visible = true,
       enter =  fadeIn(initialAlpha = 0F, animationSpec = tween(200)),
        exit = fadeOut(targetAlpha = 0F, animationSpec = tween(300))
   ){

        Scaffold{

            val coroutineScope = rememberCoroutineScope()










                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White)
                        .padding(top = 0.dp, bottom = 0.dp, start = 0.dp, end = 0.dp)
                        .onSizeChanged {
                            widthImage.value = it.width.dp
                        }
                        .background(Color(0xFFFCFDFF))
                    ,
                ) {

                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {


                        DotsIndicator()

                        Icon(imageVector = Icons.Outlined.Close,
                            contentDescription = "Back arrow",
                            tint = PowerSHRed.copy(alpha = 0.8f),
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.TopStart)
                                .background(
                                    color = CardCoverPink.copy(alpha = 0.1f),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .padding(8.dp)
                                .clickable(
                                    onClick = {
                                        isDetailScreenVisible.value = false

                                        coroutineScope.launch {
                                            bottomSheetStat.bottomSheetState.collapse()
                                        }


                                    },
                                    indication = rememberRipple(
                                        color = PowerSHRed
                                    ),
                                    interactionSource = remember { MutableInteractionSource() }
                                )


                        )


                        Icon(imageVector = if (favourite.value) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Heart",
                            tint = if (favourite.value) PowerSHRed else Color.LightGray,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(8.dp)
                                .background(
                                    color = CardCoverPink.copy(alpha = 0.1f),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .padding(8.dp)
                                .size(36.dp)
                                .clickable {
                                    favourite.value = !favourite.value
                                }
                        )

                    }

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .verticalScroll(state = rememberScrollState())

                    ) {


                        Text(
                            color = Color.DarkGray,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            text = "Nike Air Max Pro",
                            modifier = Modifier
                                .padding(top = 16.dp, start = 16.dp, end = 8.dp)
                        )
                        Text(
                            color = Color.DarkGray,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            text = "Description:",
                            modifier = Modifier
                                .padding(top = 8.dp, start = 16.dp, end = 8.dp)

                        )
                        Text(
                            color = Color.DarkGray,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Normal,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            text = "Nike Air Max is a line of shoes produced by Nike, Inc., with the first model released in 1987. Air Max shoes are identified by their midsoles incorporating flexible urethane pouches filled with pressurized gas",
                            modifier = Modifier
                                .padding(top = 8.dp, start = 16.dp, end = 8.dp)
                        )


                        features(
                            quantity = quantity,
                            isColorDialogVisible = isColorDialogVisible,
                            colorSelected = colorSelected,
                            isSizeDialogVisible = isSizeDialogVisible,
                            sizeSelected = sizeSelected,
                            isAddedToCart = isAddedToCart
                        )
                        if ( !isAddedToCart.value) {
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            )
                        }

                        AnimatedVisibility(
                            visible = isAddedToCart.value,
                            enter = fadeIn(
                                initialAlpha = 0f
                            ),
                            exit = fadeOut(
                                targetAlpha =0f
                            ),
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            CartButton(
                                quantity = quantity.value,
                                price = (quantity.value * 8000).toDouble(),
                                onClick = {
                                 //   navController.navigate(MainDestinations.CART_PAGE)
                                    pageState.value = "CART"
                                    coroutineScope.launch {
                                        bottomSheetStat.bottomSheetState.collapse()
                                    }
                                }
                            )
                        }


                        payment(
                            quantity = quantity,
                            isAddedToCart = isAddedToCart
                        )

                    }

                }


                ColorContentDialog(
                    visible = isColorDialogVisible,
                    onColorSelected = {
                        Log.d("colorItemClick", "onclick $it")
                        colorSelected.value = it
                    }
                )
                sizeContentDialog(
                    visible = isSizeDialogVisible,
                    onSizeSelected = {
                        Log.d("colorItemClick", "onclick $it")
                        sizeSelected.value = it
                    }
                )

            }











    }
}

@Composable
fun payment(
    quantity: MutableState<Int>,
    isAddedToCart: MutableState<Boolean>,
    ){



    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 8.dp, bottom = 16.dp, top = 0.dp)
    ) {


            Text(
                color = PowerSHRed,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                fontSize = 22.sp,
                text = "${quantity.value * 8000} DA",
                modifier = Modifier
                    .align(alignment = CenterVertically)
            )


        Spacer(modifier = Modifier.weight(1f))


        QuantityToggle(
            modifier = Modifier.align(CenterVertically),
            isAddedToCart = isAddedToCart,
            onIncrementQuantity = {
                quantity.value += 1
                                  },
            onDecrementQuantity = {
                quantity.value -= 1
            },
        )

    }




}



@Composable
fun features(
    quantity: MutableState<Int>,
    isColorDialogVisible: MutableState<Boolean>,
    colorSelected: MutableState<String>,
    isSizeDialogVisible: MutableState<Boolean>,
    sizeSelected: MutableState<String>,
    isAddedToCart: MutableState<Boolean>,
){

    Row(        modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 0.dp)
    ) {
        //
     //   var modifier = Modifier.align(Alignment.CenterVertically)
        var modifier = Modifier
  Column(
      modifier = Modifier.height(180.dp),
      verticalArrangement = Arrangement.SpaceEvenly
  ) {
      itemsTitle(title = "Size", modifier =  modifier)
      itemsTitle(title = "Color" , modifier =  modifier)
      itemsTitle(title = "Quantity", modifier =  modifier)
  }

        Column(
            modifier = Modifier.height(180.dp),
            verticalArrangement = Arrangement.SpaceEvenly

        ) {
            itemButton(
                value = "  ${sizeSelected.value}  ",
                modifier=  modifier,
                isColorDialogVisible = isSizeDialogVisible,
                enabled = isAddedToCart,
            )
            itemButton(
                value = colorSelected.value,
                modifier=  modifier,
                isColorDialogVisible = isColorDialogVisible,
                enabled = isAddedToCart
            )
            specialItemButton(
                value = "${quantity.value}",
                modifier =  Modifier,
                enabled = isAddedToCart,
                onAdd = {
                    quantity.value += 1
                },
                onSubstract = {
                    if (quantity.value > 1)
                    quantity.value -= 1
                    else quantity.value = 1
                }
            )
        }

    }
}


@Composable
fun itemsTitle(title: String, modifier: Modifier){
    Text(
        text = "$title:",
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold ,
        color = Color.DarkGray,
        modifier = modifier
            .padding(top = 8.dp, bottom= 8.dp)

    )
}



@Composable
fun itemButton(
    value: String, modifier: Modifier,
    isColorDialogVisible: MutableState<Boolean>,
    enabled: MutableState<Boolean>,
){

        OutlinedButton(
            enabled = !enabled.value,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = CardCoverPink,
                contentColor = Color.LightGray ,
            ),
            shape = CircleShape,
            modifier = modifier
                .padding(start = 48.dp, top = 8.dp, bottom = 8.dp)
                .width(160.dp)


            ,
            onClick = {
                isColorDialogVisible.value =! isColorDialogVisible.value
            }) {
            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal ,
                textAlign = TextAlign.Center,
                color = Color.DarkGray,
                modifier = Modifier.padding(
                    start = 36.dp,
                    end =36.dp,
                )
            )
        }

}


@Composable
fun specialItemButton(
    value: String,
    modifier: Modifier = Modifier,
    onAdd: () -> Unit = {},
    onSubstract: () -> Unit = {},
    enabled: MutableState<Boolean>
){

    OutlinedButton(
        enabled = false,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = CardCoverPink,
            contentColor = Color.LightGray ,
            disabledBackgroundColor =if (!enabled.value) CardCoverPink else MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
            disabledContentColor =if (!enabled.value) Color.LightGray else MaterialTheme.colors.onSurface.copy(alpha = 0.12f)  ,
        ),
        shape = CircleShape,
        modifier = modifier
            .padding(start = 48.dp, top = 8.dp, bottom = 8.dp)
            .width(160.dp)


        ,
        onClick = {

        }) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = "Add",
                tint = if (value.toInt() >= 10 || enabled.value) Color.LightGray else Color.DarkGray,
                modifier = Modifier
                    .clickable(
                        enabled = if (value.toInt() >= 10 || enabled.value ) false else true,
                        ) {
                        onAdd()
                    }

            )
 ClickableText(
     text = AnnotatedString(""),
     onClick = {
         if ( value.toInt() <= 10 || !enabled.value)
         onAdd()
     },
     modifier = Modifier
     .weight(1f)
 )
            Text(
                text = "$value",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = Color.DarkGray,
                modifier = Modifier.padding(
                    start = 0.dp,
                    end = 0.dp,
                )
            )

        ClickableText(
            text = AnnotatedString(""),
            onClick = {
                if ( value != "1" || !enabled.value)
                onSubstract()
            },
            modifier = Modifier
                .weight(1f)
        )
            Icon(imageVector = Icons.Outlined.Remove,
                contentDescription = "Minus",
                tint = if (value.equals("1") || enabled.value) Color.LightGray else Color.DarkGray,
                modifier = Modifier

                    .clickable(
                        enabled = if (value.equals("1") || enabled.value ) false else true,
                    ) {
                        onSubstract()
                    }


            )

        }
}




@Composable
fun threeDot(modifier: Modifier) {
    Row(
        modifier = modifier.padding(bottom = 8.dp, end = 16.dp)
    ) {

        FloatingActionButton(
            contentColor = PowerSHRed,
            backgroundColor = PowerSHRed,
            shape = CircleShape,
            modifier = Modifier
                .padding(start = 0.dp, end = 2.dp)
                .align(Alignment.CenterVertically)
                .height(8.dp)
                .width(16.dp),
            onClick = { /*TODO*/ }) {
            Spacer(modifier = Modifier.size(4.dp))
        }

        FloatingActionButton(
            contentColor =PowerSHRed.copy(alpha = 0.5f),
            backgroundColor = PowerSHRed.copy(alpha = 0.5f),
            shape = CircleShape,
            modifier = Modifier
                .padding(start = 2.dp, end = 2.dp)
                .align(Alignment.CenterVertically)
                .size(8.dp),
            onClick = { /*TODO*/ }) {

        }
        FloatingActionButton(
            contentColor = PowerSHRed.copy(alpha = 0.5f),
            backgroundColor = PowerSHRed.copy(alpha = 0.5f),
            shape = CircleShape,
            modifier = Modifier
                .padding(start = 2.dp, end = 2.dp)
                .align(Alignment.CenterVertically)
                .size(8.dp),
            onClick = { /*TODO*/ }) {

        }

    }
}



@ExperimentalPagerApi
@ExperimentalMaterialApi
@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun detailPreview() {
    val bottomSheetStat = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    val pageState = remember { mutableStateOf("CART") }

    val navController = rememberNavController()
    val isDetailScreenVisible = remember {
        mutableStateOf(true)
    }
    PowerSHTheme {
        detailScreen(navController, isDetailScreenVisible, bottomSheetStat, pageState)
    }
}