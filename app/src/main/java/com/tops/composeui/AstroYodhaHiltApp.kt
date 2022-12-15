package com.tops.composeui

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.astrologer.R
import dagger.hilt.android.HiltAndroidApp


/**
 *
 * Application class
 *
 */
val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "LocalStore")
@HiltAndroidApp
class AstroYodhaHiltApp : Application() {
    override fun onCreate() {
        super.onCreate()
        //Let's call the function.
        createNotificationChannel()
    }

    //Create Notification Channel.
    private fun createNotificationChannel(){
        val name = getString(R.string.app_name)
        val description = getString(R.string.app_name)
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        //Now Create Notification Channel.
        // it take three parameters. notification id,name, and importance.
        val channel = NotificationChannel("Global",name,importance)
        channel.description = description;

        // Get Notification Manager.
        val notificationManager : NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //Lets Create Notification channel.
        notificationManager.createNotificationChannel(channel)

    }
}