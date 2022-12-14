package com.astrologer.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.astrologer.LOGIN_SCREEN
import com.astrologer.R
import com.astrologer.WELCOME
import com.astrologer.screens.splash.SplashViewModel
import com.astrologer.theme.BrightOrange

@Composable
fun WelcomeScreen(openAndPopUp: (String, String) -> Unit,
                  modifier: Modifier = Modifier,
                  viewModel: SplashViewModel = hiltViewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
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
            Box(
                modifier = Modifier
                    .padding(0.dp, 80.dp, 0.dp, 0.dp)
                    .size(350.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hand),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Fit,
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(0.dp, 0.dp, 0.dp, 50.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Create Account",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(10.dp))
                Spacer(modifier = Modifier.size(10.dp))
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    /*Button(
                        onClick = {
                            openAndPopUp("$LOGIN_SCREEN/astrologer", WELCOME)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .padding(12.dp, 0.dp, 12.dp, 0.dp),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = White,
                            backgroundColor = BlueColor
                        )
                    ) {
                        Text(
                            text = AnnotatedString("Login to Astrologer"),
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }*/
                    Spacer(modifier = Modifier.size(8.dp))
                    Button(
                        onClick = {
                            openAndPopUp("$LOGIN_SCREEN/user", WELCOME)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .padding(12.dp, 0.dp, 12.dp, 0.dp),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = White,
                            backgroundColor = BrightOrange
                        )
                    ) {
                        Text(
                            text = AnnotatedString("Login to User"),
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}