package com.astrologer.screens.dashboard.bottomnav

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.astrologer.theme.BrightOrange
import com.astrologer.theme.GrayColor

@Composable
fun BottomNav() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Modifier.padding(it)
        BottomNavGraph(
            navController = navController
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Booking,
        BottomBarScreen.Profile)

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
                navController = navController
            )
        }
    }
}

@Composable
fun AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    val contentColor =
        if (selected) BrightOrange else GrayColor

    Box(
        modifier = Modifier
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {
        Row(
            modifier = Modifier
                .padding(start = 12.dp, end = 8.dp, top = 8.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (selected){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(20.dp).offset(4.dp, (10.unaryMinus()).dp),
                        painter = painterResource(id = screen.icon_focused),
                        contentDescription = "icon",
                        tint = contentColor
                    )
                    Text(
                        modifier = Modifier.offset(4.dp, (10.unaryMinus()).dp),
                        text = screen.title ,
                        color = contentColor,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center)

                }
            }else{
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier =Modifier.size(20.dp),
                        painter = painterResource(id = screen.icon),
                        contentDescription = "icon",
                        tint = contentColor
                    )
                    Text(
                        text = screen.title ,
                        color = contentColor,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center)
                }
            }
        }
    }
}

@Composable
@Preview
fun BottomNavPreview() {
    BottomNav()
}

















