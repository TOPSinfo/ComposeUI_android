package com.astrologer.screens.dashboard.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.astrologer.R
import com.astrologer.theme.*

@Composable
fun ProfileScreen(navController: NavHostController) {
    var mobileState by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .background(BGColor).padding(0.dp,0.dp,0.dp,100.dp)
    ) {
        Column{
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.background(White)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.size(20.dp))
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape),
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Tim Phillips",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.size(2.dp))
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Astrologer",
                        fontSize = 15.sp,
                        color = GrayColor,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(GrayBorderColor))
                    Row {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .weight(weight = 0.48f)
                                .padding(0.dp, 4.dp, 0.dp, 4.dp),
                        ) {
                            Box(Modifier
                                .background(White)
                                .height(100.dp)
                                .fillMaxWidth()) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.birthday),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(20.dp)
                                    )
                                    Text(text = "Date of Birth", color = TextColor1)
                                    Text(text = "01 May 1992", color = TextColor2)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.width(1.dp).height(100.dp).background(GrayBorderColor))


                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .weight(weight = 0.48f)
                                .padding(4.dp, 4.dp, 0.dp, 4.dp),
                        ) {
                            Box(Modifier
                                .background(White)
                                .height(100.dp)
                                .fillMaxWidth()) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.taurus_zodiac_sign),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(20.dp)
                                    )
                                    Text(text = "Sun sign", color = TextColor1)
                                    Text(text = "Taurus", color = TextColor2)
                                }
                            }
                        }
                    }


                    Spacer(modifier = Modifier.fillMaxWidth().fillMaxHeight().background(BGColor))


                    Box {
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            backgroundColor = White,
                            elevation = 8.dp,
                            border = BorderStroke(1.dp, White),
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth().height(240.dp)
                        ){
                            Column {
                                Box{
                                    Column(
                                        Modifier.fillMaxWidth().height(70.dp)) {
                                        Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
                                        Row(verticalAlignment = Alignment.CenterVertically){
                                            Spacer(modifier = Modifier.size(8.dp))
                                            Image(
                                                painter = painterResource(id = R.drawable.booking_history),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(15.dp)
                                            )
                                            Spacer(modifier = Modifier.size(8.dp))
                                            Text(text = "Booking History")
                                        }
                                        Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
                                        Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(GrayBorderColor))
                                    }
                                }
                                Box{
                                    Column(
                                        Modifier.fillMaxWidth().height(70.dp)) {
                                        Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
                                        Row(verticalAlignment = Alignment.CenterVertically){
                                            Spacer(modifier = Modifier.size(8.dp))
                                            Image(
                                                painter = painterResource(id = R.drawable.transaction_history),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(15.dp)
                                            )
                                            Spacer(modifier = Modifier.size(8.dp))
                                            Text(text = "Transaction History")
                                        }
                                        Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
                                        Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(GrayBorderColor))
                                    }
                                }
                                Box{
                                    Column(Modifier.fillMaxWidth().height(60.dp)) {
                                        Row(verticalAlignment = Alignment.CenterVertically){
                                            Spacer(modifier = Modifier.size(8.dp))
                                            Image(
                                                painter = painterResource(id = R.drawable.help),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(15.dp)
                                            )
                                            Spacer(modifier = Modifier.size(8.dp))
                                            Text(text = "Help / FAQ")
                                        }
                                        Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
                                        Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(GrayBorderColor))
                                    }
                                }
                                Box{
                                    Column(
                                        Modifier.fillMaxWidth().height(50.dp)) {
                                        Row(verticalAlignment = Alignment.CenterVertically){
                                            Spacer(modifier = Modifier.size(8.dp))
                                            Image(
                                                painter = painterResource(id = R.drawable.rate_app),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(15.dp)
                                            )
                                            Spacer(modifier = Modifier.size(8.dp))
                                            Text(text = "Rate app")
                                        }
                                        Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
                    Box {
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            backgroundColor = White,
                            elevation = 8.dp,
                            border = BorderStroke(1.dp, White),
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth().height(120.dp)
                        ) {
                            Column {
                                Box {
                                    Column(
                                        Modifier.fillMaxWidth().height(80.dp)) {
                                        Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Spacer(modifier = Modifier.size(8.dp))
                                            Image(
                                                painter = painterResource(id = R.drawable.share_app),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(15.dp)
                                            )
                                            Spacer(modifier = Modifier.size(8.dp))
                                            Text(text = "Share app")
                                        }
                                        Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
                                        Spacer(modifier = Modifier.fillMaxWidth().height(1.dp)
                                            .background(GrayBorderColor))
                                    }
                                }
                                Box (modifier = Modifier.clickable {
                                   /* navController.popBackStack()
                                    navController.navigate(LOGIN_SCREEN)*/
                                }){
                                    Column(
                                        Modifier.fillMaxWidth().height(80.dp)) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Spacer(modifier = Modifier.size(8.dp))
                                            Image(
                                                painter = painterResource(id = R.drawable.logout),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(15.dp)
                                            )
                                            Spacer(modifier = Modifier.size(8.dp))
                                            Text(text = "Logout")
                                        }
                                        Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
