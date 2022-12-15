package com.tops.composeui.screens

import androidx.lifecycle.ViewModel
import com.tops.composeui.common.snackbar.SnackbarManager
import com.tops.composeui.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import com.tops.composeui.model.service.LogService
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