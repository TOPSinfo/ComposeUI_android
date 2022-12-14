package com.astrologer.model.service.impl

import com.astrologer.model.SignupVO
import com.astrologer.model.service.StorageService
import com.google.firebase.firestore.DocumentChange.Type.REMOVED
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import javax.inject.Inject


/**
 * Storage Service Implementation
 */

class StorageServiceImpl @Inject constructor() : StorageService {
    private var listenerRegistration: ListenerRegistration? = null

    override fun addListener(
        userId: String,
        onDocumentEvent: (Boolean, SignupVO) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val query = Firebase.firestore.collection(USER_COLLECTION).whereEqualTo(USER_ID, userId)
        listenerRegistration = query.addSnapshotListener { value, error ->
            if (error != null) {
                onError(error)
                return@addSnapshotListener
            }
            value?.documentChanges?.forEach {
                val wasDocumentDeleted = it.type == REMOVED
                val task = it.document.toObject<SignupVO>().copy(uid = it.document.id)
                onDocumentEvent(wasDocumentDeleted, task)
            }
        }
    }

    override fun removeListener() {
        listenerRegistration?.remove()
    }

    override fun saveUser(signupVO: SignupVO, onResult: (Throwable?) -> Unit) {
        Firebase.firestore
            .collection(USER_COLLECTION)
            .add(signupVO)
            .addOnCompleteListener { onResult(it.exception) }
    }

    override fun getUser(
        userID: String,
        onError: (Throwable) -> Unit,
        onSuccess: (SignupVO) -> Unit
    ) {
    }

    override fun updateUser(signupVO: SignupVO, onResult: (Throwable?) -> Unit) {
    }

    override fun deleteUser(userID: String, onResult: (Throwable?) -> Unit) {
    }

    override fun deleteAllForUser(userId: String, onResult: (Throwable?) -> Unit) {
    }

    companion object {
        private const val USER_COLLECTION = "user"
        private const val USER_ID = "userId"
    }
}