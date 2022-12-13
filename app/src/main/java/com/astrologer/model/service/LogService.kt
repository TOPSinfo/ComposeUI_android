package com.astrologer.model.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable?)
}
