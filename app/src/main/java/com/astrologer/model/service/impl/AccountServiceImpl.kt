package com.astrologer.model.service.impl

import android.app.Activity
import android.util.Log
import com.astrologer.model.service.AccountService
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AccountServiceImpl @Inject constructor() : AccountService {
    private lateinit var omVerificationCode: String

    override fun linkPhoneAccount(
        mobile: String,
        activity: Activity,
        onResult: (String, Throwable?) -> Unit,
    ) {
        val auth = Firebase.auth
      /*  auth.firebaseAuthSettings.setAppVerificationDisabledForTesting(false)
        auth.firebaseAuthSettings.forceRecaptchaFlowForTesting(true)
*/
        auth.firebaseAuthSettings.setAppVerificationDisabledForTesting(true)


        val onVerificationCallback =
            object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    Log.e("p0", "++++++" + p0.smsCode)
                    onResult("", null)
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    Log.e("p0", "++++++" + p0.message)
                    onResult("", p0)
                }

                override fun onCodeSent(
                    verificationCode: String,
                    p1: PhoneAuthProvider.ForceResendingToken,
                ) {
                    super.onCodeSent(verificationCode, p1)
                    omVerificationCode = verificationCode
                    onResult(omVerificationCode, null)
                }
            }
        val options = PhoneAuthOptions.newBuilder(auth).setPhoneNumber(mobile)
            .setTimeout(60L, TimeUnit.SECONDS).setActivity(activity)
            .setCallbacks(onVerificationCallback).build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    override fun verifyPhoneAccount(
        verificationID: String,
        code: String,
        activity: Activity,
        onResult: (String, Throwable?) -> Unit,
    ) {
        val auth = Firebase.auth
        auth.firebaseAuthSettings.setAppVerificationDisabledForTesting(false)
        auth.firebaseAuthSettings.forceRecaptchaFlowForTesting(true)
        val credential = PhoneAuthProvider.getCredential(verificationID, code)
        auth.signInWithCredential(credential).addOnCompleteListener(activity) { task ->
            if (task.isSuccessful) {
                val user = task.result?.user
                onResult(user!!.uid, null)
            } else {
                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    onResult("", task.exception)
                }
            }
        }
    }

    override fun signOut() {
        Firebase.auth.signOut()
    }


    override fun getUser(
        phoneNumberWithCode: String,
        isLogin: Boolean,
        onError: (Throwable) -> Unit, onSuccess: (String) -> Unit,
    ) {

        var isExits = false
        var uID = ""

        Firebase.firestore.collection("user").get().addOnFailureListener { error ->
            onError(error)
        }.addOnSuccessListener { result ->
            for (document in result.documents) {
                val phone = document.get("phone")
                if (phone.toString() == phoneNumberWithCode) {
                    uID = document.get("uid").toString()
                    isExits = true
                    break
                } else {
                    uID = document.get("uid").toString()
                    isExits = false
                }
            }
            if (isLogin) {
                if (isExits) {
                    onSuccess(uID)
                } else {
                    onSuccess("")
                }
            }
        }
    }
}