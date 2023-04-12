package com.tops.composeui.screens.signup

import android.app.Activity
import android.os.Build
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.tops.composeui.OTP_SCREEN
import com.tops.composeui.SIGN_UP_SCREEN
import com.tops.composeui.common.ext.isValidEmail
import com.tops.composeui.common.snackbar.SnackbarManager
import com.tops.composeui.model.SignupVO
import com.tops.composeui.model.service.AccountService
import com.tops.composeui.model.service.LogService
import com.tops.composeui.screens.AstroYodhaViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.perf.ktx.performance
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.sql.Timestamp
import javax.inject.Inject
import com.astrologer.R.string as AppText



/**
 * Signup view model
 * */

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService, logService: LogService,
) : AstroYodhaViewModel(logService) {
    var uiState = mutableStateOf(SignUpUiState())
        private set


    val isLogin get() = uiState.value.isLogin
    val fullName get() = uiState.value.fullName
    val token get() = uiState.value.token
    val mobile get() = uiState.value.mobile
    val countryCode get() = uiState.value.countryCode

    var isLoader = mutableStateOf(false)

    private val email get() = uiState.value.email
    private val isCheck get() = uiState.value.isCheck

    fun onCountryCodeChange(newValue: String) {
        uiState.value = uiState.value.copy(countryCode = newValue)
    }


    fun onMobileChange(newValue: String) {
        uiState.value = uiState.value.copy(mobile = newValue)
    }

    fun onTermsCondition(newValue: Boolean) {
        uiState.value = uiState.value.copy(isCheck = newValue)
    }

    fun onFullNameChange(newValue: String) {
        uiState.value = uiState.value.copy(fullName = newValue)
    }

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onSignUpClick(type: String, activity: Activity, openAndPopUp: (String, String) -> Unit) {
        if (!isLogin) {
            if (fullName.isEmpty()) {
                SnackbarManager.showMessage(AppText.please_enter_name)
                return
            }

            if (mobile.isEmpty()) {
                SnackbarManager.showMessage(AppText.please_enter_mobile)
                return
            }

            if (!email.isValidEmail()) {
                SnackbarManager.showMessage(AppText.email_error)
                return
            }
            if (!isCheck) {
                SnackbarManager.showMessage(AppText.please_accept)
                return
            }
        } else {
            if (mobile.isEmpty()) {
                SnackbarManager.showMessage(AppText.please_enter_mobile)
                return
            }
        }

        var newCountryCode = ""

        newCountryCode = if (countryCode.isEmpty()) {
            "+91"
        } else {
            countryCode
        }
        val phoneNumberWithCode = newCountryCode + mobile


        viewModelScope.launch(showErrorExceptionHandler) {
            saveUser(type,activity,openAndPopUp,phoneNumberWithCode)
        }
    }

    private fun saveUser(
        type: String,
        activity: Activity,
        openAndPopUp: (String, String) -> Unit,
        phoneNumberWithCode: String
    ){
        viewModelScope.launch(showErrorExceptionHandler) {
            isLoader.value = true
            val createAccountTrace = Firebase.performance.newTrace(CREATE_ACCOUNT_TRACE)
            createAccountTrace.start()
            accountService.linkPhoneAccount(phoneNumberWithCode,
                activity) { verificationID, error ->
                createAccountTrace.stop()
                if (verificationID.isNotEmpty()) {
                    //SnackbarManager.showMessage(AppText.otp_send)
                    val user = SignupVO("",
                        "",
                        "",
                        Timestamp(System.currentTimeMillis()),
                        Timestamp(System.currentTimeMillis()),
                        Build.MODEL,
                        email,
                        fullName,
                        "",
                        false,
                        phoneNumberWithCode,
                        "",
                        "",
                        "",
                        token,
                        "",
                        type,
                        0,
                        verificationID,
                        isLogin)
                    val json = Gson().toJson(user)
                    openAndPopUp("$OTP_SCREEN/$json", SIGN_UP_SCREEN)
                    isLoader.value = false
                } else {
                    onError(error!!)
                    isLoader.value = false
                }
            }
        }
    }

    companion object {
        private const val CREATE_ACCOUNT_TRACE = "createAccount"
    }
}