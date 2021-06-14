package com.esi.sba.powersh.components.indicator


import android.content.Context
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationSpec
import com.airbnb.lottie.compose.rememberLottieAnimationState
import com.esi.sba.powersh.R
import com.esi.sba.powersh.ui.theme.PowerSHBlue
import com.esi.sba.powersh.ui.theme.PowerSHBrown
import com.esi.sba.powersh.ui.theme.PowerSHRed
import com.esi.sba.powersh.ui.theme.PowerSHTheme

import kotlinx.coroutines.delay

val onboardingItems by lazy {
    listOf(
        OnboardingPage.Page1,
        OnboardingPage.Page2,
        OnboardingPage.Page3
    )
}


@Composable
fun OnboardingScreen(
    context: Context,
    skip: () -> Unit = {}
) {

    val items = onboardingItems

    OnboardingScreenBody(items = items) {
      PreferenceManager(context).hasOnboarding = false
       skip()
    }


}

@Composable
fun OnboardingScreenBody(items: List<OnboardingPage>, onFinish: () -> Unit) {

    val pagerState = remember { PagerState() }
   val (isFinish, setFinish) = remember { mutableStateOf(false) }

    pagerState.maxPage = (items.size - 1).coerceAtLeast(0)
    val onboardingPage = items[pagerState.currentPage]
    val color = if (isFinish) MaterialTheme.colors.primary else onboardingPage.color
    val backgroundColor by animateColorAsState(
        color,
        spring(DampingRatioLowBouncy, StiffnessLow)
    )

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        val (pagerIndicator, button, options, wave, waveBody) = createRefs()
        val guideline = createGuidelineFromBottom(0.2f)
        val guidelineWave = createGuidelineFromTop(0.5f)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .constrainAs(waveBody) {
                    top.linkTo(parent.top)
                    bottom.linkTo(guidelineWave)
                    height = Dimension.fillToConstraints
                }
        )
        Image(
            painter = painterResource(R.drawable.ic_wave),
            contentDescription = "image",
            modifier = Modifier
                .aspectRatio(4.5f)
                .constrainAs(wave) {
                    top.linkTo(guidelineWave)
                },
            colorFilter = ColorFilter.tint(backgroundColor)
        )
        Pager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) {
            OnboardingPage(items[page], isFinish)
        }

       if (!isFinish) {

           PageIndicator(
               pagesCount = items.count(),
               currentPageIndex = pagerState.currentPage,
               color = PowerSHRed,
               modifier = Modifier
                   .padding(16.dp)
                   .constrainAs(pagerIndicator) {
                       top.linkTo(guideline)
                       linkTo(start = parent.start, end = parent.end)
                   }
           )


           OnboardingOptions(
               visible = pagerState.currentPage != pagerState.maxPage,
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(16.dp)
                   .constrainAs(options) {
                       bottom.linkTo(parent.bottom)
                   },
               skip = {
                   setFinish(true)
               },
               next = {
                   pagerState.currentPage += 1
               }
           )


           GetStartedButton(
               visible = pagerState.currentPage == pagerState.maxPage,
               modifier = Modifier.constrainAs(button) {
                   end.linkTo(parent.end)
                   start.linkTo(parent.start)
                   bottom.linkTo(parent.bottom, 24.dp)
                   width = Dimension.fillToConstraints
               },
               finish = {
                   setFinish(true)
               }
           )
       }
    }

    LaunchedEffect(isFinish) {
       if (isFinish) {
            onFinish()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GetStartedButton(
    visible: Boolean,
    modifier: Modifier = Modifier,
    finish: () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = slideInHorizontally(initialOffsetX = { 1300 }, animationSpec = tween()),
        exit = fadeOut()
    ) {
        Button(
            modifier = Modifier.wrapContentWidth(),
            onClick = finish,
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = PowerSHRed),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp)
        ) {
            Text(
                text = "Get started",
                style = MaterialTheme.typography.button,
                color = Color.White
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OnboardingOptions(
    visible: Boolean,
    modifier: Modifier = Modifier,
    skip: () -> Unit,
    next: () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        exit = fadeOut()
    ) {
        ConstraintLayout(Modifier.fillMaxWidth()) {
            val (textSkip, textNext) = createRefs()
            TextButton(
                onClick = skip,
                modifier = Modifier
                    .constrainAs(textSkip) {
                        start.linkTo(parent.start)
                    }
            ) {
                Text(
                    text = "Skip",
                    style = MaterialTheme.typography.button.merge(TextStyle(fontWeight = FontWeight.Medium)),
                    color = PowerSHRed
                )
            }

            TextButton(
                onClick = next,
                modifier = Modifier
                    .constrainAs(textNext) {
                        end.linkTo(parent.end)
                    }
            ) {
                Text(
                    text ="Next",
                    style = MaterialTheme.typography.button,
                    color = PowerSHRed
                )
            }
        }
    }
}

@Composable
fun OnboardingPage(item: OnboardingPage, isFinish: Boolean) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        if (!isFinish) {
            val (image, title, subtitle) = createRefs()
            val guideline = createGuidelineFromBottom(0.2f)
            val animationSpec = remember { LottieAnimationSpec.RawRes(item.animation) }
            val animationState =
                rememberLottieAnimationState(autoPlay = true, repeatCount = Integer.MAX_VALUE)
            LottieAnimation(
                spec = animationSpec,
                animationState = animationState,
                modifier = Modifier
                    .padding(56.dp)
                    .constrainAs(image) {
                        linkTo(
                            start = parent.start,
                            top = parent.top,
                            end = parent.end,
                            bottom = title.top
                        )
                    },
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Medium,
                color = PowerSHRed,
                modifier = Modifier
                    .constrainAs(title) {
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                        bottom.linkTo(subtitle.top, 16.dp)
                        width = Dimension.fillToConstraints
                    },
                textAlign = TextAlign.Center
            )
            Text(
                text = item.subtitle,
                color = Color.Gray,
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .constrainAs(subtitle) {
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                        bottom.linkTo(guideline, 32.dp)
                        width = Dimension.fillToConstraints
                    },
                textAlign = TextAlign.Center
            )
        }
    }
}
