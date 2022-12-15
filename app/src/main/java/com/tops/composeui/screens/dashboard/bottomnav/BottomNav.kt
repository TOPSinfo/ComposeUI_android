package com.tops.composeui.screens.dashboard.bottomnav

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tops.composeui.screens.dashboard.screens.BookingScreen
import com.tops.composeui.screens.dashboard.screens.HomeScreen
import com.tops.composeui.screens.dashboard.screens.ProfileScreen
import com.tops.composeui.theme.BrightOrange
import com.tops.composeui.theme.GrayColor

/**
 * Bottom navigation view screen
 */

@Composable
fun BottomNav(
    navController: NavHostController
) {
    val mutableIndex = remember {
        mutableStateOf(0)
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            BottomBar(
                navController = navController,
                mutableIndex = mutableIndex
            )
        },
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            when (mutableIndex.value) {
                0 -> {
                    HomeScreen(navController = navController)
                }
                1 -> {
                    BookingScreen()
                }
                2 -> {
                    ProfileScreen(navController = navController)
                }
                3 -> {
                    ProfileScreen(navController = navController)
                }
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    mutableIndex: MutableState<Int>
) {


    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Booking,
        BottomBarScreen.Profile
    )

    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Row(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController,
                mutableIndex = mutableIndex
            )
        }
    }
}

@Composable
fun AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
    mutableIndex: MutableState<Int>
) {
    val contentColor =
        if (screen.index == mutableIndex.value) BrightOrange else GrayColor

    Box(
        modifier = Modifier
            .clickable(onClick = {
                mutableIndex.value = screen.index
            })
    ) {
        Row(
            modifier = Modifier
                .padding(start = 12.dp, end = 8.dp, top = 8.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (screen.index == mutableIndex.value) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(20.dp)
                            .offset(4.dp, (10.unaryMinus()).dp),
                        painter = painterResource(id = screen.icon_focused),
                        contentDescription = "icon",
                        tint = contentColor
                    )
                    Text(
                        modifier = Modifier.offset(4.dp, (10.unaryMinus()).dp),
                        text = screen.title,
                        color = contentColor,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center
                    )

                }
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = screen.icon),
                        contentDescription = "icon",
                        tint = contentColor
                    )
                    Text(
                        text = screen.title,
                        color = contentColor,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
















