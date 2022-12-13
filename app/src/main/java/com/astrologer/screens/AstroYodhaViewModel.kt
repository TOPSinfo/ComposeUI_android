package com.astrologer.screens

import androidx.lifecycle.ViewModel
import com.astrologer.common.snackbar.SnackbarManager
import com.astrologer.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import com.astrologer.model.service.LogService
import kotlinx.coroutines.CoroutineExceptionHandler

open class AstroYodhaViewModel(private val logService: LogService) : ViewModel() {
    open val showErrorExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }
    open val logErrorExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        logService.logNonFatalCrash(throwable)
    }
    open fun onError(error: Throwable) {
        SnackbarManager.showMessage(error.toSnackbarMessage())
        logService.logNonFatalCrash(error)
    }
}