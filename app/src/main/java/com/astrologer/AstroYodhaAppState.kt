package com.astrologer

import android.app.Activity
import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import com.astrologer.common.snackbar.SnackbarManager
import com.astrologer.common.snackbar.SnackbarMessage.Companion.toMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@Stable
class AstroYodhaAppState(
    val activity: Activity,
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    private val snackbarManager: SnackbarManager,
    private val resources: Resources,
    coroutineScope: CoroutineScope
) {
    init {
        coroutineScope.launch {
            snackbarManager.snackbarMessages.filterNotNull().collect {
                    snackbarMessage ->
                val text = snackbarMessage.toMessage(resources)
                scaffoldState.snackbarHostState.showSnackbar(text)
            }
        }
    }

    fun popUp() {
        navController.popBackStack()
    }

    fun navigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
        }
    }

    fun navigateAndPopUp(route: String, popUp: String) {
       /* navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }*/
        if(popUp== SPLASH_SCREEN || popUp== HOME_SCREEN){
            //navController.popBackStack()
        }
        navController.navigate(route)
    }

    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }
}