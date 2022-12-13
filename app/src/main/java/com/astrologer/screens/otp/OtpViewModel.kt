package com.astrologer.screens.otp

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.viewModelScope
import com.astrologer.*
import com.astrologer.common.snackbar.SnackbarManager
import com.astrologer.model.SignupVO
import com.astrologer.model.service.AccountService
import com.astrologer.model.service.LogService
import com.astrologer.model.service.StorageService
import com.astrologer.screens.AstroYodhaViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.perf.ktx.performance
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    fun saveUserData(
        task: SignupVO,
        context: Context,
        coroutineScope: CoroutineScope,
        openAndPopUp: (String, String) -> Unit
    ) {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                val id = stringPreferencesKey("uID")
                context.dataStore.edit {
                        pref ->
                    pref[id] = task.uid
                    if(task.isLogin){
                        openAndPopUp(HOME_SCREEN, LOGIN_SCREEN)
                    }else{
                        openAndPopUp(HOME_SCREEN, SIGN_UP_SCREEN)
                    }
                }
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