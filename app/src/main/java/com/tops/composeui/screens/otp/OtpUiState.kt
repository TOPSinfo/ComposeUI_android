package com.tops.composeui.screens.otp

data class OtpUiState(
    var otp: String = "",
    var user: String= "",
    var token: String= "",
    var isLogin: Boolean =false
)
