package com.astrologer.screens.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.astrologer.AstroYodhaAppState
import com.astrologer.LOGIN_SCREEN
import com.astrologer.R
import com.astrologer.SIGN_UP_SCREEN
import com.astrologer.screens.sign_up.SignUpViewModel
import com.astrologer.theme.BrightOrange
import com.astrologer.theme.GrayColor
import com.astrologer.utils.CountryCodePicker
import com.astrologer.utils.countrypicker.model.getListCountries
import com.astrologer.utils.showProgress


@Composable
fun LoginScreen(
    appState: AstroYodhaAppState,
    type: String,
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState
    uiState.isLogin = true

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 10.dp, 10.dp, 10.dp),
    ) {
        Column(
            modifier = Modifier,
        ) {
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
                    text = "Login",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(2.dp))
                Text(textAlign = TextAlign.Center,
                    text = "Enter your mobile number to create an \naccount or sign in",
                    fontSize = 15.sp,
                    color = GrayColor,
                    fontWeight = FontWeight.Normal)
                Spacer(modifier = Modifier.size(20.dp))
                val countryCode = CountryCodePicker()
                countryCode.CountryCodeDialog(viewModel,
                    pickedCountry = {
                        Log.v("TAG", "country name is : ${it.countryName}")
                        viewModel.onCountryCodeChange(it.countryPhoneCode)
                    },
                    defaultSelectedCountry = getListCountries().single { it.countryCode == "in" },
                    dialogSearch = true,
                    dialogRounded = 22)
                Spacer(modifier = Modifier.size(20.dp))
                Button(onClick = {
                    viewModel.onSignUpClick(type, appState.activity, openAndPopUp)
                },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(12.dp, 0.dp, 12.dp, 0.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(contentColor = White,
                        backgroundColor = BrightOrange)) {
                    Text(text = "Login", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.size(40.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(text = "Not registered yet? ",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal)
                    ClickableText(text = AnnotatedString("Sign Up"),
                        onClick = {
                            openAndPopUp("$SIGN_UP_SCREEN/$type", LOGIN_SCREEN)
                        },
                        style = TextStyle(fontSize = 15.sp,
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Bold,
                            color = BrightOrange))
                }
            }

            if(viewModel.isLoader.value){
                showProgress()
            }
        }
    }
}
