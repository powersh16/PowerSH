package com.esi.sba.powersh.screens

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.esi.sba.powersh.MainDestinations
import com.esi.sba.powersh.model.CardItem
import com.esi.sba.powersh.model.DataProvider
import com.esi.sba.powersh.model.Step
import com.esi.sba.powersh.ui.theme.CardCoverPink
import com.esi.sba.powersh.ui.theme.PowerSHRed
import com.esi.sba.powersh.ui.theme.PowerSHTheme
import kotlin.math.absoluteValue

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun checkoutScreen(
    navController: NavController,
    cartProduct: MutableList<CardItem>,
){

    val step = remember {
        mutableStateOf(1)
    }

 Scaffold(
     bottomBar = {
          Row(
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(16.dp, 16.dp)
          ) {


      AnimatedVisibility(
          visible = step.value > 0,
          enter = fadeIn(
              initialAlpha = 0f, tween(100)) + expandIn(
              expandFrom = Alignment.Center,
              animationSpec = spring(),
          ),
          exit = fadeOut(targetAlpha = 0f, tween(100)) + shrinkOut(),
      ) {
          OutlinedButton(
              shape = RoundedCornerShape(14.dp),
              colors = ButtonDefaults.outlinedButtonColors(contentColor =  PowerSHRed),
              modifier = Modifier
             //     .background(color = PowerSHRed, shape = RoundedCornerShape(14.dp))
                  .align(Alignment.CenterVertically),
              onClick = {
                  if (step.value > 0)
                  step.value -= 1
              }) {
              Text(
                  text = "Back" ,
                  color = PowerSHRed,
                  style = TextStyle(
                      background = Color.White,
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



              Spacer(
                  modifier =
                  Modifier
                      .weight(1f)
              )



              Button(
                  shape = RoundedCornerShape(14.dp),
                  colors = ButtonDefaults.buttonColors(
                      backgroundColor =PowerSHRed ,
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
                      text =  if (step.value < 3) "Next" else "Confirm" ,
                      color =Color.White,
                      style = TextStyle(
                          background = PowerSHRed,
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


 }


}


@Composable
fun StepItem(
    s : Step,
    index : Int,
    lastIndex : Int,
    selected :  Boolean,
    modifier : Modifier
){

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
                    //  if (index != 0) {
                    end.linkTo(parent.end)
                    //   }
                    //    if (index != lastIndex) {
                    start.linkTo(parent.start)

                    //   }
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
){

    val steps = remember { DataProvider.stepList }

    // steps : MutableList<Step>

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 16.dp)
    ) {

        steps.forEachIndexed { index, s ->
            StepItem(
                s,
                index,
                steps.size - 1,
                index <= step.value  ,
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
            navController =navController,
            cartProduct = cartProduct)
    }
}