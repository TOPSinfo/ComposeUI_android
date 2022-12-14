package com.astrologer.model.service.impl

import com.astrologer.model.service.LogService
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import javax.inject.Inject


/**
 * Log Service Implementation
 */

class LogServiceImpl @Inject constructor() : LogService {
    override fun logNonFatalCrash(throwable: Throwable?) {
        if (throwable != null) Firebase.crashlytics.recordException(throwable)
    }
}