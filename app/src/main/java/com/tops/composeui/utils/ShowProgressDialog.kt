package com.tops.composeui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.astrologer.R

/**
 *
 * Custom Loader
 *
 */

@Composable
fun showProgress() {
    Dialog(onDismissRequest = {},
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(100.dp)
                .background(White, shape = RoundedCornerShape(12.dp))) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator(modifier = Modifier
                    .size(40.dp)
                    .padding(8.dp, 4.dp, 8.dp, 0.dp),
                    color = colorResource(id = R.color.bright_orange),
                    strokeWidth = Dp(value = 1F))
                Text(text = "Please wait...", Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp))
            }
        }
    }
}