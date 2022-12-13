package com.astrologer.screens.dashboard.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.astrologer.NOTIFICATION
import com.astrologer.screens.dashboard.screens.HomeScreen
import com.astrologer.screens.dashboard.screens.ProfileScreen
import com.astrologer.screens.dashboard.screens.ReportScreen
import com.astrologer.screens.notification.NotificationScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Booking.route) {
            ReportScreen()
        }
        composable(route = BottomBarScreen.Wallet.route) {
            ReportScreen()
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(NOTIFICATION) {
            NotificationScreen(navController = navController)
        }
    }
}