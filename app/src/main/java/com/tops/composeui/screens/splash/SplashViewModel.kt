package com.tops.composeui.screens.splash

import com.tops.composeui.HOME_SCREEN
import com.tops.composeui.SPLASH_SCREEN
import com.tops.composeui.WELCOME
import com.tops.composeui.model.service.LogService
import com.tops.composeui.screens.AstroYodhaViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * Splash screen view model
 * */

@HiltViewModel
class SplashViewModel @Inject constructor(
    logService: LogService,
) : AstroYodhaViewModel(logService) {

    fun onAppStart(isLogin: Boolean, openAndPopUp: (String, String) -> Unit) {
        if (isLogin) {
            openAndPopUp(HOME_SCREEN, SPLASH_SCREEN)
        } else {
            openAndPopUp(WELCOME, SPLASH_SCREEN)
        }
    }
}