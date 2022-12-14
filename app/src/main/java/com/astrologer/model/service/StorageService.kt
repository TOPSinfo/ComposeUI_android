package com.astrologer.model.service

import com.astrologer.model.SignupVO

/**
 * StorageService implement
 */

interface StorageService {
    fun addListener(
        userId: String,
        onDocumentEvent: (Boolean, SignupVO) -> Unit,
        onError: (Throwable) -> Unit
    )
    fun removeListener()
    fun saveUser(signupVO: SignupVO, onResult: (Throwable?) -> Unit)
    fun getUser(userID: String, onError: (Throwable) -> Unit, onSuccess: (SignupVO) -> Unit)
    fun updateUser(signupVO: SignupVO, onResult: (Throwable?) -> Unit)
    fun deleteUser(userID: String, onResult: (Throwable?) -> Unit)
    fun deleteAllForUser(userId: String, onResult: (Throwable?) -> Unit)
}
