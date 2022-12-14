package com.astrologer.common.ext

import android.util.Patterns

/**
 * Check the email validation
 */

fun String.isValidEmail(): Boolean {
    return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}