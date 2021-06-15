package com.esi.sba.powersh.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.esi.sba.powersh.components.extensions.*
import com.esi.sba.powersh.model.Product
import com.esi.sba.powersh.ui.theme.CardCoverPink
import com.esi.sba.powersh.ui.theme.PowerSHRed
import com.esi.sba.powersh.ui.theme.YellowOnboarding
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun DotsIndicator(list: MutableList<Product>) {
    var scope = rememberCoroutineScope()
    val dotSettings =
        IndicatorState.DotSettings(size = list.size, radius = 6f, color = PowerSHRed)
    val pagerState = rememberPagerState(pageCount = list.size, initialOffscreenLimit = 2)
    val state = remember { IndicatorState(scope, dotSettings) }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 0.dp)
            .background(YellowOnboarding.copy(alpha = 0.05f))
    ) {

        HorizontalPager(
            state = pagerState,
            itemSpacing = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
        ) { page ->

            Card(
                Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                        scaleX = lerp(
                            start = 0.92f.dp,
                            stop = 1f.dp,
                            fraction = 1f  - pageOffset.coerceIn(0f, 1f)
                        ).value

                        scaleY = lerp(
                            start = 0.63f.dp,
                            stop = 1f.dp,
                            fraction = 1f  - pageOffset.coerceIn(0f, 1f)
                        ).value

                        alpha = lerp(
                            start = 0.8f.dp,
                            stop = 1f.dp,
                            fraction = 1f  - pageOffset.coerceIn(0f, 1f)
                        ).value
                    }
                    .fillMaxWidth(0.8f)
                    .height(240.dp)
                    .padding(top = 32.dp , bottom = 8.dp)
                    .border(0.5.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                ,
                elevation = 0.5f.dp,
                shape = RoundedCornerShape(8.dp)
            ) {

                    Image(
                        painter = rememberCoilPainter(
                            list[page].ImageId,
                            fadeIn = true
                        ),
                        contentDescription = "Shoes",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .offset {
                                val pageOffset =
                                    this@HorizontalPager.calculateCurrentOffsetForPage(page)
                                IntOffset(
                                    x = (24.dp * pageOffset).roundToPx(),
                                    y = 0
                                )
                            }
                    )

                   Column(
                        Modifier
                            .fillMaxWidth()
                            .height(240.dp)
                            .align(Alignment.Center)
                            .background(
                                color = CardCoverPink.copy(alpha = 0.1f)
                            )
                    ) {}


            }

        }

        if (list.size > 1) {
            Indicators(
                state,
                pagerState,
                Modifier
                    .fillMaxWidth()
                    .padding(0.dp, top = 0.dp, 0.dp, 0.dp)
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun Indicators(state: IndicatorState, pagerState: PagerState, modifier: Modifier) {
    if (pagerState.isScrollInProgress && state.targetPosition != pagerState.targetValue()) {
        state.startScrolling(pagerState.targetValue()!!)
    } else if (!pagerState.isScrollInProgress) {
        state.finishScrolling()
    }
    emptyIndicators(state = state, modifier = modifier)
    filledIndicators(state = state)
}


@Composable
fun emptyIndicators(state: IndicatorState, modifier: Modifier) {
    var dotSettings = state.dotSettings
    Canvas(
        modifier = modifier
    ) {
        state.setFirstIndicatorPosition(center)
        for (i in 0 until state.dotSettings.size) {
            drawCircle(
                color = dotSettings.color,
                radius = dotSettings.radius,
                center = Offset(
                    state.firstDotPosition!!.x + dotSettings.distanceBetweenDots * i,
                    state.firstDotPosition!!.y
                ),
                alpha = 0.2f
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun filledIndicators(state: IndicatorState) {
    firstFilledDot(state)
    secondFilledDot(state)
    drawUnion(state)
}