package com.astrologer.screens.dashboard.bottomnav

import com.astrologer.R


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
) {

    // for home
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.ic_home,
        icon_focused = R.drawable.ic_home_fill
    )

    // for report
    object Booking: BottomBarScreen(
        route = "Bookings",
        title = "My Bookings",
        icon = R.drawable.ic_calendar,
        icon_focused = R.drawable.ic_calendar_fill
    )

    // for report
    object Wallet: BottomBarScreen(
        route = "wallet",
        title = "Wallet",
        icon = R.drawable.ic_wallet,
        icon_focused = R.drawable.ic_wallet_fill
    )

    // for report
    object Profile: BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = R.drawable.ic_user,
        icon_focused = R.drawable.ic_user_fill
    )
}
