package com.esi.sba.powersh.components


import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.akram.app1.screen.colorGridItem
import com.akram.app1.screen.mColorsIdsGridList
import com.akram.app1.screen.mSizeIdGridList
import com.akram.app1.screen.sizeGridItem
import com.esi.sba.powersh.ui.theme.PowerSHRed

private enum class QuantityToggleState { Zero, NonZero }

@Composable
fun QuantityToggle(
    modifier: Modifier = Modifier,
    isAddedToCart: MutableState<Boolean>,
    onIncrementQuantity: () -> Unit,
    onDecrementQuantity: () -> Unit,
) {

    val transition = updateTransition(
        if (!isAddedToCart.value) QuantityToggleState.Zero else QuantityToggleState.NonZero,
        label = ""
    )

    val backgroundColor by transition.animateColor { state ->
        when (state) {
            QuantityToggleState.Zero -> PowerSHRed
            QuantityToggleState.NonZero -> MaterialTheme.colors.secondary
        }
    }

    val contentColor by transition.animateColor { state ->
        when (state) {
            QuantityToggleState.Zero -> PowerSHRed
            QuantityToggleState.NonZero -> LocalContentColor.current
        }
    }

    val iconSize by transition.animateDp { state ->
        when (state) {
            QuantityToggleState.Zero -> 0.dp
            QuantityToggleState.NonZero -> 18.dp
        }
    }


//
    Button(
        border = BorderStroke(2.dp, PowerSHRed),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
        ),
        shape = RoundedCornerShape(14.dp),
        modifier = modifier
            .padding(end = 8.dp)


        ,
        onClick = {
            Log.d("", "OnClick")
           // if (!isAddedToCart.value) isAddedToCart.value else onDecrementQuantity()
            isAddedToCart.value = !isAddedToCart.value
        }) {


        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (isAddedToCart.value) {

                Icon(
                    Icons.Rounded.Done,
                    contentDescription = "ADDED TO CART",
                    tint = if (!isAddedToCart.value) Color.White else PowerSHRed,
                    modifier = Modifier
                        .padding(
                            start = 12.dp,
                            end = 12.dp,
                            top = 0.dp,
                            bottom = 0.dp
                        )
                        .size(iconSize)

                )
            }
            Text(
                text = if (!isAddedToCart.value) "ADD TO CART" else "ADDED",
                style = MaterialTheme.typography.button,
                textAlign = TextAlign.Center,
                color = if (!isAddedToCart.value) Color.White else PowerSHRed,
                fontStyle = FontStyle.Normal,
                fontWeight =if (!isAddedToCart.value) FontWeight.Normal else FontWeight.SemiBold,
                fontSize = 18.sp,
                modifier = Modifier
                    .animateContentSize()
                    .padding(
                        start = if (!isAddedToCart.value) 12.dp else 0.dp,
                        end = 12.dp,
                        top = 0.dp,
                        bottom = 0.dp
                    )
            )
        }
    }
}







@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
@Composable
fun ColorContentDialog(
    visible: MutableState<Boolean>,
    onColorSelected: (String) -> Unit
) {

    AnimatedVisibility(
        visible = visible.value,
        enter = slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300)),
    ) {


        if (visible.value) {
            ContentDialog(
                title = "Select your favourite color",
                onDismissRequest = { visible.value = false },
            ) {
                Grid(
                    list = mColorsIdsGridList,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) { id ->

                    Log.d("AkramTag", "isode EmojiGrid id = $id")


                    colorGridItem(
                        id = id,
                        onSelected = {
                            onColorSelected(it)
                            Log.d("AkramTag2", "isode EmojiGridItem id = $it")
                            visible.value = false
                        }
                    )

                }
            }
        }



    }

}






@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
@Composable
fun sizeContentDialog(
    visible: MutableState<Boolean>,
    onSizeSelected: (String) -> Unit
) {

    AnimatedVisibility(
        visible = visible.value,
        enter = slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300)),
    ) {


        if (visible.value) {
            ContentDialog(
                title = "Select your shoes size",
                onDismissRequest = { visible.value = false },
            ) {
                Grid(
                    list = mSizeIdGridList,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) { id ->

                    Log.d("AkramTag", "isode size size id = $id")


                    sizeGridItem(
                        id = id,
                        onSelected = {
                            onSizeSelected(it)
                            Log.d("AkramTag2", "isode size size id = $it")
                            visible.value = false
                        }
                    )

                }
            }
        }



    }

}







@Composable
fun <T> Grid(
    list: List<List<T?>>,
    modifier: Modifier = Modifier,
    itemContent: @Composable (item: T?) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        for (row in list) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (item in row) {
                    itemContent(item)
                }
            }
        }
    }
}






@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ContentDialog(
    title: String,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    onClick = onDismissRequest
                )
        ) {
            Surface(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.h6.copy(fontSize = 18.sp) ,
                        textAlign = TextAlign.Center,
                        color = Color.DarkGray,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                    )

                    Spacer(
                        modifier = Modifier
                            .height(16.dp)
                            .fillMaxWidth()
                    )

                    content()
                }
            }
        }
    }
}











