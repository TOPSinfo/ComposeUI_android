package com.tops.composeui.screens.dashboard.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tops.composeui.screens.dashboard.model.onGoingList
import com.tops.composeui.screens.dashboard.model.pastList
import com.tops.composeui.screens.dashboard.model.upcomingList
import com.tops.composeui.theme.BrightOrange
import com.tops.composeui.theme.GrayColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import com.astrologer.R


/**
 * Booking history screen
 */


@OptIn(ExperimentalPagerApi::class)
@Composable
fun BookingScreen() {
    val tabDataList = remember {
        tabDataList()
    }

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = tabDataList.size)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(50.dp).padding(10.dp)) {
            Spacer(modifier = Modifier.size(4.dp))
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = "My Bookings",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.weight(1f),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cal),
                    contentDescription = "calendar",
                    modifier = Modifier
                        .size(20.dp)
                        .align(alignment = Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.size(12.dp))
                Image(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = "add",
                    modifier = Modifier
                        .size(20.dp)
                        .align(alignment = Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.size(4.dp))
            }
        }


        TabRow(
            selectedTabIndex = pagerState.currentPage,
            divider = {
                Spacer(modifier = Modifier.height(5.dp))
            },
            backgroundColor = Color.White,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    height = 3.dp,
                    color = BrightOrange
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            tabDataList.forEachIndexed { index, s ->
                Tab(selected = pagerState.currentPage == index, onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                    text = {
                        Text(text = s.tabName, color = if(pagerState.currentPage==index) BrightOrange else GrayColor)
                    })
            }
        }

        HorizontalPager(state = pagerState) { pager ->
            when (pager) {
                0 -> {
                    BookingListItem(list = upcomingList())
                }

                1 -> {
                    BookingListItem(list = onGoingList())
                }

                2 -> {
                    BookingListItem(list = pastList())
                }
            }
        }
    }
}