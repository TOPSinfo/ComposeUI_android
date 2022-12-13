package com.astrologer.screens.splash

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.hilt.navigation.compose.hiltViewModel
import com.astrologer.R
import com.astrologer.dataStore
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


private const val SPLASH_TIMEOUT = 1000L

@Composable
fun SplashScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
        if (!task.isSuccessful) {
            return@OnCompleteListener
        }
        val token = task.result
        if (token != null) {
            saveToken(token, context, coroutineScope)
        }
    })

    val uID = stringPreferencesKey("uID")
    var flag = false
    flow {
        context.dataStore.data.map {
            it[uID]
        }.collect(collector = {
            if (it != null) {
                Log.d("++++++++", it)
                flag = it.toString().isNotEmpty()
                this.emit(it)
            }
        })
    }.collectAsState(initial = "")


    LaunchedEffect(true) {
        delay(SPLASH_TIMEOUT)
        viewModel.onAppStart(flag, openAndPopUp)
    }


    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(id = R.mipmap.splash_bg),
            contentDescription = "",
            contentScale = ContentScale.FillBounds, // or some other scale
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = "Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(180.dp)
            )
        }
    }
}

fun saveToken(token: String, context: Context, coroutineScope: CoroutineScope) {
    coroutineScope.launch {
        withContext(Dispatchers.IO) {
            val gckTokenKey = stringPreferencesKey("gcm_token")
            context.dataStore.edit { pref ->
                pref[gckTokenKey] = token
            }
        }
    }
}


