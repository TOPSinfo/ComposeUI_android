package com.tops.composeui.screens.otp


/**
 * Data class to store and Retrieve
 * Otp view ui state
 * */
data class OtpUiState(
    var otp: String = "",
    var user: String= "",
    var token: String= "",
    var isLogin: Boolean =false
)
