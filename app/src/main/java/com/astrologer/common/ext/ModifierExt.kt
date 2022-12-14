package com.astrologer.common.ext

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Adding padding
 */

fun Modifier.basicButton(): Modifier {
    return this.fillMaxWidth().padding(16.dp, 8.dp)
}
