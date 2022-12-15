package com.tops.composeui.model.service

/**
 * LogService implement
 */

interface LogService {
    fun logNonFatalCrash(throwable: Throwable?)
}
