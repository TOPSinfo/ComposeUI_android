package com.astrologer.screens.splash

import com.astrologer.HOME_SCREEN
import com.astrologer.SPLASH_SCREEN
import com.astrologer.WELCOME
import com.astrologer.model.service.LogService
import com.astrologer.screens.AstroYodhaViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

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