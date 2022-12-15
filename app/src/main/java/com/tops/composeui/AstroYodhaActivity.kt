package com.tops.composeui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * Application main class to redirect first screen
 *
 */

@AndroidEntryPoint
@ExperimentalMaterialApi
class AstroYodhaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AstroYodhaApp(this)
        }
    }
}
