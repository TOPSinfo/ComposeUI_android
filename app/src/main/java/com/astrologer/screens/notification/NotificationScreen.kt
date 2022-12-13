package com.astrologer.screens.notification

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.astrologer.R
import com.astrologer.component.CustomTopAppBar
import com.astrologer.theme.LineColor

data class NotificationModel(val title: String, var desc: String, val icon: Int)

private val notificationList = mutableListOf<NotificationModel>()

@Composable
fun NotificationScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        NotificationPage(navController)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotificationPage(navController: NavHostController) {
    notificationList.clear()
    notificationList.add(
        NotificationModel(
            "Give Review",
            "This app is great...!", R.drawable.notification_star
        )
    )
    notificationList.add(
        NotificationModel(
            "Astro Balance",
            "Wow..You added $50...", R.drawable.notification_wallet
        )
    )
    notificationList.add(
        NotificationModel(
            "Event will start in 10 mints",
            "Your event will start in 10 mints....", R.drawable.notification_icon
        )
    )
    notificationList.add(
        NotificationModel(
            "Event Added",
            "Your event added successfully..!", R.drawable.notification_calendar
        )
    )
    Scaffold(
        topBar = {
            CustomTopAppBar(navController, "Notification", true)
        }, content = {
            Box(modifier = Modifier.background(Color.White)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {
                        items(notificationList) { model ->
                            ListRow(model = model)
                        }
                    }
                }
            }
        })
}

@Composable
fun ListRow(model: NotificationModel) {
    Column {
        Column(modifier = Modifier.padding(10.dp)) {
            Row {
                Spacer(modifier = Modifier.size(4.dp))
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.weight(weight = 0.15f),
                ) {
                    Image(
                        painter = painterResource(id = model.icon),
                        contentDescription = "verified",
                        modifier = Modifier
                            .size(40.dp)
                            .align(alignment = Alignment.CenterVertically)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.weight(weight = 0.7f),
                ) {
                    Column {
                        Text(
                            text = model.title,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = model.desc,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.weight(weight = 0.15f),
                ) {
                    Text(
                        text = "Today",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
        }
        Divider(color = LineColor, thickness = 1.dp)
        Spacer(modifier = Modifier.size(8.dp))
    }
}