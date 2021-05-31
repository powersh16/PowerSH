package com.esi.sba.powersh.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.esi.sba.powersh.MainDestinations
import com.esi.sba.powersh.R
import com.esi.sba.powersh.ui.theme.CardCover
import com.esi.sba.powersh.ui.theme.PowerSHRed
import com.esi.sba.powersh.ui.theme.PowerSHTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


sealed class DrawerScreens(val title: String) {
    object Home : DrawerScreens("Home")
    object Account : DrawerScreens("Cart")
    object Help : DrawerScreens( "Favourite")
}

private val screens = listOf(
    DrawerScreens.Home,
    DrawerScreens.Account,
    DrawerScreens.Help
)




@Composable
fun mainDrawer(
    navController: NavController,
    modifier: Modifier = Modifier,
    scope: CoroutineScope,
    selectedScreen: MutableState<String>,
    scaffoldState: ScaffoldState,
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = 48.dp)
            .background(color = CardCover)
    ) {


        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(MainDestinations.REGESTRATION_PAGE)
                }
        ) {
            Image(
                painter = painterResource(R.drawable.ic_user),
                contentDescription = "App icon",
                modifier = Modifier
                    .size(108.dp)
                    .padding(start = 16.dp)

            )

            Column(
                modifier = Modifier
                    .align(CenterVertically)

            ) {
                Text(
                    text = "Akram Bensalem",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.Black,

                    )
                Text(
                    text = "ak.bensalem@gmail.com",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.DarkGray,
                )
            }


        }




        Column(
            modifier = Modifier.padding(top = 48.dp)
        ) {

            DrawerRow(
                title=  "Home",
                icon = Icons.Outlined.Home,
                id=  "HOME",
                scope = scope,
                selectedScreen= selectedScreen,
                scaffoldState = scaffoldState,
            )



        Spacer(modifier = Modifier.padding(8.dp))
            DrawerRow(
                title=  "Cart",
                icon = Icons.Outlined.ShoppingCart,
                id=  "CART",
                scope = scope,
                selectedScreen= selectedScreen,
                scaffoldState = scaffoldState,
            )



            Spacer(modifier = Modifier.padding(8.dp))
        DrawerRow(
            title=  "Favourite",
            icon = Icons.Outlined.FavoriteBorder,
            id=  "FAVOURITE",
            scope = scope,
            selectedScreen= selectedScreen,
            scaffoldState = scaffoldState,
        )

        Spacer(modifier = Modifier.padding(8.dp))
        DrawerRow(
            title=  "Orders",
            icon = Icons.Outlined.LocalShipping,
            id=  "ORDERS",
            scope = scope,
            selectedScreen= selectedScreen,
            scaffoldState = scaffoldState,
        )
        Spacer(modifier = Modifier.padding(8.dp))
        DrawerRow(
            title=  "Settings",
            icon = Icons.Outlined.Settings,
            id=  "SETTINGS",
            scope = scope,
            selectedScreen= selectedScreen,
            scaffoldState = scaffoldState,
        )
        Spacer(modifier = Modifier.padding(8.dp))
        DrawerRow(
            title=  "About Us",
            icon = Icons.Outlined.Info,
            id=  "ABOUT",
            scope = scope,
            selectedScreen= selectedScreen,
            scaffoldState = scaffoldState,
        )


        Spacer(modifier = Modifier.padding(8.dp))
        DrawerRow(
            title=  "Contact Us",
            icon = Icons.Outlined.MailOutline,
            id=  "CONTACT",
            scope = scope,
            selectedScreen= selectedScreen,
            scaffoldState = scaffoldState,
        )

    }
  }
}


@OptIn(ExperimentalStdlibApi::class)
@Composable
fun DrawerRow(
    title: String,
    icon : ImageVector,
    id: String,
    scope: CoroutineScope,
    selectedScreen: MutableState<String>,
    scaffoldState: ScaffoldState,
){
    Row(modifier = Modifier
        .clickable {
            scope.launch {
                scaffoldState.drawerState.close()
                selectedScreen.value = id.uppercase()
            }
        }
        .fillMaxWidth()
        .padding(0.dp)
        .padding(start = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Icon(
            icon,
            contentDescription = title,
            modifier = Modifier.size(36.dp),
            tint =if (selectedScreen.value.equals(id.uppercase())) PowerSHRed else Color.DarkGray,
        )
        Text(
            text = title,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .align(CenterVertically)
                .fillMaxWidth()
            ,
            color =if (selectedScreen.value.equals(id.uppercase())) PowerSHRed else Color.DarkGray,

            )
    }

}





@Preview
@Composable
fun drawerPreview() {

    val navController = rememberNavController()

    val scaffoldState = rememberScaffoldState(
        rememberDrawerState(DrawerValue.Closed)
    )

    val selectedItem = remember { mutableStateOf("HOME") }


    val scope = rememberCoroutineScope()

    PowerSHTheme {
        mainDrawer(
            navController,
            scope = scope,
            selectedScreen = selectedItem,
            scaffoldState = scaffoldState
        )
    }
}