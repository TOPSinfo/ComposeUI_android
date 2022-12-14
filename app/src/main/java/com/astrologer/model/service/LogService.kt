package com.astrologer.model.service

/**
 * LogService implement
 */

interface LogService {
    fun logNonFatalCrash(throwable: Throwable?)
}
