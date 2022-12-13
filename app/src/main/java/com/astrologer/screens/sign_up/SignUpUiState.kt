package com.astrologer.screens.sign_up

data class SignUpUiState(
    val email: String = "",
    val fullName: String = "",
    val mobile: String = "",
    var countryCode: String = "",
    var token: String = "",
    val isCheck: Boolean =false,
    var isLogin: Boolean =false
)
