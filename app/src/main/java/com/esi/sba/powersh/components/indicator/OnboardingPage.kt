package com.esi.sba.powersh.components.indicator

import androidx.annotation.RawRes
import androidx.compose.ui.graphics.Color
import com.esi.sba.powersh.R
import com.esi.sba.powersh.ui.theme.BlueOnboardingLight
import com.esi.sba.powersh.ui.theme.OrangeOnboarding
import com.esi.sba.powersh.ui.theme.PurpleOnboarding
import com.esi.sba.powersh.ui.theme.YellowOnboarding


sealed class OnboardingPage(
     val title: String,
     val subtitle: String,
    @RawRes val animation: Int,
    val color: Color
) {
    object Page1 : OnboardingPage(
        "Choose a Product","Choose any product that you like from out store.", R.raw.image, YellowOnboarding)
    object Page2 : OnboardingPage(
        "Add To Cart","Add the product to your shopping cart", R.raw.mobile, OrangeOnboarding)
    object Page3 : OnboardingPage(
        "Confirm Order","Fill your dilevery information and wait for your product at home.", R.raw.product_page, BlueOnboardingLight)
}