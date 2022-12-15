package com.tops.composeui.model.service

import android.app.Activity


/**
 * AccountService implement
 */

interface AccountService {
    fun linkPhoneAccount(mobile: String , activity: Activity , onResult: (String,Throwable?) -> Unit)
    fun verifyPhoneAccount(verificationID: String ,code: String , activity: Activity , onResult: (String,Throwable?) -> Unit)
    fun signOut()
    fun getUser(phoneNumberWithCode:String,isLogin:Boolean,onError: (Throwable) -> Unit, onSuccess: (String) -> Unit)
}
