package com.tops.composeui.screens.otp

import android.app.Activity
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.firebase.ktx.Firebase
import com.google.firebase.perf.ktx.performance
import com.google.gson.Gson
import com.tops.composeui.HOME_SCREEN
import com.tops.composeui.LOGIN_SCREEN
import com.tops.composeui.SIGN_UP_SCREEN
import com.tops.composeui.common.snackbar.SnackbarManager
import com.tops.composeui.model.SignupVO
import com.tops.composeui.model.service.AccountService
import com.tops.composeui.model.service.LogService
import com.tops.composeui.model.service.StorageService
import com.tops.composeui.screens.AstroYodhaViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.astrologer.R.string as AppText

@HiltViewModel
class OtpViewModel @Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageService,
    logService: LogService
) : AstroYodhaViewModel(logService) {
    var uiState = mutableStateOf(OtpUiState())
        private set

    private val otp get() = uiState.value.otp
    private val result get() = uiState.value.user

    fun onOtpChange(newValue: String) {
        uiState.value = uiState.value.copy(otp = newValue)
    }

    fun onVerifyOtpClick(
        activity: Activity,
        openAndPopUp: (String, String) -> Unit,
        coroutineScope: CoroutineScope
    ) {
        if (otp.isEmpty()) {
            SnackbarManager.showMessage(AppText.please_enter_otp)
            return
        }
        viewModelScope.launch(showErrorExceptionHandler) {
            val signupVO = Gson().fromJson(result, SignupVO::class.java)
            val createAccountTrace = Firebase.performance.newTrace(VERIFY)
            createAccountTrace.start()
            accountService.verifyPhoneAccount(signupVO.verificationID,otp , activity) { uID ,error ->
                createAccountTrace.stop()
                if (uID.isNotEmpty()) {
                    signupVO.uid = uID
                    saveUser(signupVO,activity,coroutineScope,openAndPopUp)
                } else {
                    onError(error!!)
                }
            }
        }
    }

    private fun saveUser(
        task: SignupVO,
        activity: Activity,
        coroutineScope: CoroutineScope,
        openAndPopUp: (String, String) -> Unit
    ) {
        val saveTaskTrace = Firebase.performance.newTrace(SAVE_USER_TRACE)
        saveTaskTrace.start()

        storageService.saveUser(task) { error ->
            saveTaskTrace.stop()
            //saveUserData(task,activity,coroutineScope,openAndPopUp)
            if(task.isLogin){
                openAndPopUp(HOME_SCREEN, LOGIN_SCREEN)
            }else{
                openAndPopUp(HOME_SCREEN, SIGN_UP_SCREEN)
            }
        }
    }

    companion object {
        private const val UTC = "UTC"
        private const val DATE_FORMAT = "EEE, d MMM yyyy"
        private const val SAVE_USER_TRACE = "saveUser"
        private const val UPDATE_USER_TRACE = "updateUser"
        private const val VERIFY = "verify"
    }


}