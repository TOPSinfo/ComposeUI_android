package com.tops.composeui

import android.app.Activity
import android.content.res.Resources
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tops.composeui.common.snackbar.SnackbarManager
import com.tops.composeui.screens.dashboard.bottomnav.BottomNav
import com.tops.composeui.screens.login.LoginScreen
import com.tops.composeui.screens.notification.NotificationPage
import com.tops.composeui.screens.otp.OtpScreen
import com.tops.composeui.screens.signup.SignUpScreen
import com.tops.composeui.screens.splash.SplashScreen
import com.tops.composeui.screens.welcome.WelcomeScreen
import com.tops.composeui.theme.AstroYodhaAppTheme
import kotlinx.coroutines.CoroutineScope


/**
 *
 * Application main class to redirect first screen
 *
 */

@Composable
@ExperimentalMaterialApi
fun AstroYodhaApp(activity: Activity) {
    AstroYodhaAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            val appState = rememberAppState(activity)
            Scaffold(
                snackbarHost = {
                    SnackbarHost(
                        hostState = it,
                        modifier = Modifier.padding(8.dp),
                        snackbar = { snackbarData ->
                            Snackbar(snackbarData, contentColor = MaterialTheme.colors.onPrimary)
                        }
                    )
                },
                scaffoldState = appState.scaffoldState
            ) { innerPaddingModifier ->
                NavHost(
                    navController = appState.navController,
                    startDestination = SPLASH_SCREEN,
                    modifier = Modifier.padding(innerPaddingModifier)
                ) {
                    astroYodhaGraph(appState)
                }
            }
        }
    }
}

@Composable
fun rememberAppState(
    activity: Activity,
      scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(activity,scaffoldState, navController, snackbarManager, resources, coroutineScope) {
    AstroYodhaAppState(activity,scaffoldState, navController, snackbarManager, resources, coroutineScope)
}

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@ExperimentalMaterialApi
fun NavGraphBuilder.astroYodhaGraph(appState: AstroYodhaAppState) {
    composable(SPLASH_SCREEN) {
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
    composable(WELCOME) {
        WelcomeScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
    composable(
        "$LOGIN_SCREEN/{type}",
        arguments = listOf(
            navArgument("type") { type = NavType.StringType }
        )
    ) { backStackEntry ->
        backStackEntry.arguments?.getString("type")?.let { type ->
            LoginScreen(appState,type,openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
        }
    }

    composable(
        "$OTP_SCREEN/{user}",
        arguments = listOf(
            navArgument("user") { type = NavType.StringType }
        )
    ) { backStackEntry ->
        backStackEntry.arguments?.getString("user")?.let { user ->
            OtpScreen(appState,user,openAndPopUp = {
                    route,
                    popUp -> appState.navigateAndPopUp(route, popUp)
            })
        }
    }
    composable(
        "$SIGN_UP_SCREEN/{type}",
        arguments = listOf(
            navArgument("type") { type = NavType.StringType }
        )
    ) { backStackEntry ->
        backStackEntry.arguments?.getString("type")?.let { type ->
            SignUpScreen(appState,type,openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
        }
    }
    composable(HOME_SCREEN) {
        BottomNav(
            appState.navController
        )
    }

    composable(NOTIFICATION) {
        NotificationPage(appState.navController)
    }
}