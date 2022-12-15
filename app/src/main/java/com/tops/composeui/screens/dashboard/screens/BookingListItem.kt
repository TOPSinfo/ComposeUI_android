package com.tops.composeui.screens.dashboard.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tops.composeui.screens.dashboard.model.BookingModel
import com.tops.composeui.theme.GrayColor
import com.tops.composeui.theme.LightBlueColor


/**
 * BottomNavGraph for the bottom tab
 */

@Composable
fun BookingListItem(
    list: MutableList<BookingModel>
) {
    val data = remember {
        list
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
    ) {
        LazyColumn {
            items(
                items = data
            ) { item ->
                ItemView(item)
            }
        }
    }
}

@Composable
fun ItemView(reportModel: BookingModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                8.dp
            ).background(
                color = LightBlueColor,
                shape = RoundedCornerShape(
                    16.dp
                )
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(
                    color = reportModel.color,
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        bottomStart = 16.dp
                    )
                ).padding(
                    start = 8.dp
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = reportModel.date,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Center
                )
            )
        }

        Column(
            Modifier
                .padding(
                start = 8.dp,
                end = 8.dp
            )
        ) {
            Text(
                text = reportModel.header,
                modifier = Modifier.fillMaxSize(),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                )
            )

            Text(
                text = reportModel.time,
                modifier = Modifier.fillMaxSize(),
                style = TextStyle(
                    color = GrayColor,
                    fontSize = 12.sp,
                )
            )

            Text(
                text = reportModel.rate,
                modifier = Modifier.fillMaxSize(),
                style = TextStyle(
                    color = GrayColor,
                    fontSize = 12.sp,
                )
            )

            Text(
                text = reportModel.status,
                modifier = Modifier.fillMaxSize(),
                style = TextStyle(
                    color = GrayColor,
                    fontSize = 12.sp,
                )
            )
        }


    }

}
