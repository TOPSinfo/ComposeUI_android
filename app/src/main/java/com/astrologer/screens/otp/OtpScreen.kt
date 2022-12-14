package com.astrologer.screens.otp
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.astrologer.AstroYodhaAppState
import com.astrologer.R
import com.astrologer.model.SignupVO
import com.astrologer.theme.BrightOrange
import com.astrologer.theme.GrayColor
import com.astrologer.theme.LightBlueColor
import com.google.gson.Gson
import kotlinx.coroutines.delay

@Composable
fun OtpScreen(
    appState: AstroYodhaAppState,
    user: String,
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OtpViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState
    uiState.user = user
    val coroutineScope = rememberCoroutineScope()

    val signupVO = Gson().fromJson( uiState.user, SignupVO::class.java)

    var isNextBtnStatus by remember {
        mutableStateOf(false)
    }
    var smsCodeNumber by remember {
        mutableStateOf("")
    }

    var visible by remember {
        mutableStateOf(false)
    }


    val totalTime = 10L * 3000L
    // create variable for current time
    var currentTime by remember {
        mutableStateOf(totalTime)
    }
    // create variable for isTimerRunning
    var isTimerRunning by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if(currentTime > 0 && isTimerRunning) {
            delay(100L)
            currentTime -= 100L
            visible = currentTime.toString()=="0"
        }
    }
    LaunchedEffect(true) {
        delay(1000L)
        if(currentTime <= 0L) {
            currentTime = totalTime
            isTimerRunning = true
        } else {
            isTimerRunning = !isTimerRunning
        }
    }


    Box(
        modifier
            .fillMaxSize()
            .padding(10.dp, 10.dp, 10.dp, 10.dp),
    ) {
        Column(modifier = Modifier, verticalArrangement = Arrangement.Top) {
            Spacer(modifier = Modifier.size(20.dp))
            Image(painter = painterResource(id = R.drawable.back),
                contentDescription = "Back",
                modifier = Modifier
                    .size(50.dp)
                    .padding(0.dp, 0.dp, 8.dp, 8.dp)
                    .clickable {
                       appState.popUp()
                    })
            Column(modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Spacer(modifier = Modifier.size(20.dp))
                Image(painter = painterResource(id = R.drawable.ic_login),
                    contentDescription = "login",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(0.dp, 0.dp, 8.dp, 8.dp))
                Spacer(modifier = Modifier.size(20.dp))
                Text(textAlign = TextAlign.Center,
                    text = "OTP Verification",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(2.dp))
                Text(textAlign = TextAlign.Center,
                    text = "Enter OTP Code sent to "+signupVO.phone+" .",
                    fontSize = 15.sp,
                    color = GrayColor,
                    fontWeight = FontWeight.Normal)
                Spacer(modifier = Modifier.size(20.dp))
                SmsCodeView(smsCodeLength = 6,
                    textFieldColors = TextFieldDefaults.textFieldColors(backgroundColor = LightBlueColor),
                    textStyle = MaterialTheme.typography.h6,
                    smsFulled = {
                        smsCodeNumber = it
                        isNextBtnStatus = it.length == 6
                    })


                if (isNextBtnStatus) {
                    viewModel.onOtpChange(smsCodeNumber)
                }

                Spacer(modifier = Modifier.size(20.dp))
                Button(onClick = {
                    viewModel.onVerifyOtpClick(appState.activity, openAndPopUp,coroutineScope)
                },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(8.dp, 0.dp, 12.dp, 0.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(contentColor = White,
                        backgroundColor = BrightOrange)) {
                    Text(text = "Verify", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.size(40.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(30.dp).padding(8.dp, 4.dp, 8.dp, 0.dp),
                        color = colorResource(id = R.color.bright_orange),
                        strokeWidth = Dp(value = 1F)
                    )
                    Text(text = "Resend OTP available in " + (currentTime / 1000L).toString(),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black)
                }
                Spacer(modifier = Modifier.size(40.dp))
                AnimatedVisibility(visible) {
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .padding(8.dp, 0.dp, 12.dp, 0.dp),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(contentColor = White,
                            backgroundColor = BrightOrange),
                    ) {
                        Text(text = "Resend OTP on SMS",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.size(10.dp))
                /*Button(onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(8.dp, 0.dp, 12.dp, 0.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(contentColor = White,
                        backgroundColor = BrightOrange)) {
                    Text(text = "Get OTP Via Call", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                }*/
            }
        }
    }
}
