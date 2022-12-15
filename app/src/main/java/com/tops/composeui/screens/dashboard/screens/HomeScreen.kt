package com.tops.composeui.screens.dashboard.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tops.composeui.NOTIFICATION
import com.astrologer.R
import com.tops.composeui.screens.dashboard.model.GridModal
import com.tops.composeui.theme.BlueColor
import com.tops.composeui.theme.BrightOrange
import com.tops.composeui.theme.GrayBorderColor
import com.tops.composeui.theme.LightOrange


/**
 * Home screen with the listing
 */


@Composable
fun HomeScreen(navController: NavHostController) {
    val flag = true
    Box (modifier = Modifier.verticalScroll(rememberScrollState()).padding(0.dp,0.dp,0.dp,100.dp)){
        Box(modifier = Modifier) {
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = "BG",
                modifier = Modifier.fillMaxWidth()
            )
            Column {
                Spacer(modifier = Modifier.size(20.dp))
                Row {
                    Spacer(modifier = Modifier.size(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(
                            text = "Namaste, Darsh!",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Image(
                            painter = painterResource(id = R.drawable.emojione_hand_with_fingers_splayed),
                            contentDescription = "hand",
                            modifier = Modifier
                                .size(20.dp)
                        )
                    }
                    Image(
                        painter = painterResource(id =  if (flag) R.drawable.fill_notification else R.drawable.notification),
                        contentDescription = "notification",
                        modifier = Modifier
                            .size(35.dp)
                            .padding(0.dp, 0.dp, 8.dp, 0.dp)
                            .clickable{
                                navController.navigate(NOTIFICATION)
                            })

                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp, 0.dp, 0.dp, 0.dp),
                    text = "Welcome!!",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp, 0.dp, 0.dp, 0.dp),
                    text = "19 November 2021",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.size(30.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp, 0.dp, 8.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Astrologers",
                        maxLines = 1,
                        fontSize = 18.sp,
                        color = Black,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        "View all",
                        fontSize = 15.sp,
                        color = Black,
                    )
                }
                GridView(context = LocalContext.current)
                Box {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        backgroundColor = BrightOrange,
                        elevation = 8.dp,
                        border = BorderStroke(1.dp, GrayBorderColor),
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .size(0.dp, 150.dp),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                Modifier
                                    .padding(10.dp),
                            ) {
                                Text(
                                    "Astrologer",
                                    fontSize = 12.sp,
                                    color = White,
                                    fontWeight = FontWeight.Normal
                                )
                                Spacer(modifier = Modifier.size(4.dp))
                                Text(
                                    "Connect with astrologer \nby booking" +
                                            "an appointment.",
                                    fontSize = 15.sp,
                                    color = White,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.size(20.dp))
                                Button(
                                    onClick = {
                                    },
                                    modifier = Modifier
                                        .height(40.dp)
                                        .width(150.dp),
                                    shape = RoundedCornerShape(4.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        contentColor = BrightOrange,
                                        backgroundColor = White
                                    )
                                ) {
                                    Text(
                                        text = "Book Appointment",
                                        fontSize = 12.sp,
                                        color = BrightOrange,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }

                    Image(
                        painter = painterResource(id = R.drawable.appointment_banner),
                        contentDescription = "appointment",
                        modifier = Modifier
                            .size(210.dp).align(alignment = Alignment.TopEnd)
                            .offset(0.dp, (-55).dp),
                    )
                }
                Text(
                    modifier = Modifier.padding(10.dp,0.dp,0.dp,0.dp),
                    text = "upcoming",
                    fontSize = 18.sp,
                    color = Black,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.size(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        backgroundColor = White,
                        elevation = 8.dp,
                        border = BorderStroke(1.dp, BrightOrange),
                        modifier = Modifier
                            .padding(8.dp)
                            .width(100.dp)
                            .height(100.dp)
                    ) {
                        Column(
                            modifier = Modifier,
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.daily_horo),
                                contentDescription = "notification",
                                modifier = Modifier
                                    .size(30.dp)
                            )
                            Text(
                                "Daily Horoscope",
                                fontSize = 9.sp,
                                color = BrightOrange,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        backgroundColor = White,
                        elevation = 8.dp,
                        border = BorderStroke(1.dp, BrightOrange),
                        modifier = Modifier
                            .padding(8.dp)
                            .width(100.dp)
                            .height(100.dp)
                    ) {
                        Column(
                            modifier = Modifier,
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.free_kundli),
                                contentDescription = "notification",
                                modifier = Modifier
                                    .size(30.dp)
                            )
                            Text(
                                "Free Kundali",
                                fontSize = 9.sp,
                                color = BlueColor,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        backgroundColor = White,
                        elevation = 8.dp,
                        border = BorderStroke(1.dp, BrightOrange),
                        modifier = Modifier
                            .padding(8.dp)
                            .width(100.dp)
                            .height(100.dp)
                    ) {
                        Column(
                            modifier = Modifier,
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.hero_matching),
                                contentDescription = "notification",
                                modifier = Modifier
                                    .size(30.dp)
                            )
                            Text(
                                "Horoscope Matching",
                                fontSize = 9.sp,
                                color = LightOrange,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }
            }
        }
    }
}


/**
 * Listing of people
 */


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GridView(context: Context) {
    lateinit var courseList: List<GridModal>
    courseList = ArrayList()

    courseList = courseList + GridModal("Prasanta", R.drawable.banner_1)
    courseList = courseList + GridModal("Nikhil", R.drawable.banner_3)
    courseList = courseList + GridModal("Ritu", R.drawable.banner_4)
    courseList = courseList + GridModal("Heena", R.drawable.banner_2)


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(10.dp).height(480.dp)
    ) {
        items(courseList.size) {
            Card(
                shape = RoundedCornerShape(8.dp),
                elevation = 8.dp,
                border = BorderStroke(1.dp, GrayBorderColor),
                onClick = {
                    Toast.makeText(
                        context,
                        courseList[it].name + " selected..",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier.padding(8.dp),
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                ) {
                    Image(
                        painter = painterResource(id = courseList[it].img),
                        contentDescription = courseList[it].name,
                        modifier = Modifier.clip(RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Spacer(modifier = Modifier.size(4.dp))
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.weight(1f),
                        ) {
                            Text(
                                text = courseList[it].name,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal
                            )
                            Spacer(modifier = Modifier.size(1.dp))
                            Image(
                                painter = painterResource(id = R.drawable.verified),
                                contentDescription = "verified",
                                modifier = Modifier
                                    .size(12.dp)
                                    .align(alignment = Alignment.CenterVertically)
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.weight(1f),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.active_rating),
                                contentDescription = "verified",
                                modifier = Modifier
                                    .size(12.dp)
                                    .align(alignment = Alignment.CenterVertically)
                            )
                            Spacer(modifier = Modifier.size(1.dp))
                            Text(
                                text = "4.5",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        text = "Numerology, Face Read...",
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedButton(
                        onClick = {},
                        border = BorderStroke(1.dp, BrightOrange),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = BrightOrange,
                            backgroundColor = White,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                    ) {
                        Text(
                            text = "Book Now",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}