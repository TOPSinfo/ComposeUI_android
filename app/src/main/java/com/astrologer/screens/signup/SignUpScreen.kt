package com.astrologer.screens.signup

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.hilt.navigation.compose.hiltViewModel
import com.astrologer.AstroYodhaAppState
import com.astrologer.R
import com.astrologer.common.composable.BasicButton
import com.astrologer.common.ext.basicButton
import com.astrologer.dataStore
import com.astrologer.theme.BGColor
import com.astrologer.theme.BrightOrange
import com.astrologer.theme.GrayColor
import com.astrologer.utils.CountryCodePicker
import com.astrologer.utils.countrypicker.model.getListCountries
import com.astrologer.utils.showProgress
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import com.astrologer.R.string as AppText

@Composable
fun SignUpScreen(
    appState: AstroYodhaAppState,
    type:String,
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel()
) {

    var nameState by remember {
        mutableStateOf("")
    }
    var emailState by remember {
        mutableStateOf("")
    }

    val checkedState = remember { mutableStateOf(false) }
    val uiState by viewModel.uiState

    uiState.isLogin = false

    val context = LocalContext.current
    val gckTokenKey = stringPreferencesKey("gcm_token")

    flow {
        context.dataStore.data.map {
            it[gckTokenKey]
        }.collect(collector = {
            if (it != null) {
                Log.d("FCM", it)
                uiState.token = it.toString()
                this.emit(it)
            }
        })
    }.collectAsState(initial = "")

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .background(BGColor).padding(10.dp,10.dp,10.dp,100.dp)
    ) {
        Column(
            modifier = Modifier, verticalArrangement = Arrangement.Top
        ) {
            Image(painter = painterResource(id = R.drawable.back),
                contentDescription = "Back",
                modifier = Modifier
                    .size(50.dp)
                    .padding(0.dp, 0.dp, 8.dp, 8.dp)
                    .clickable {
                        appState.popUp()
                    })
            Spacer(modifier = Modifier.size(30.dp))
            Text(
                text = "Create\nAccount", fontSize = 25.sp, fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.size(20.dp))
            TextField(modifier = Modifier.fillMaxWidth(),
                value = nameState,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = White,
                    //cursorColor = Color.Black,
                    disabledLabelColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = { newText ->
                    nameState = newText
                    viewModel.onFullNameChange(nameState)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                placeholder = { Text("Full Name") },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "user",
                        modifier = Modifier.size(20.dp)
                    )
                })
            Spacer(modifier = Modifier.size(10.dp))

            val countryCode = CountryCodePicker()
            countryCode.CountryCodeDialog(
                viewModel,
                pickedCountry = {
                    Log.v("TAG", "country name is : ${it.countryName}")
                    viewModel.onCountryCodeChange(it.countryPhoneCode)
                },
                defaultSelectedCountry = getListCountries().single { it.countryCode == "in" },
                dialogSearch = true,
                dialogRounded = 22
            )
            Spacer(modifier = Modifier.size(10.dp))
            TextField(modifier = Modifier.fillMaxWidth(),
                value = emailState,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = White,
                    //cursorColor = Color.Black,
                    disabledLabelColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = { newText ->
                    emailState = newText
                    viewModel.onEmailChange(emailState)
                },
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                placeholder = { Text("Email Address") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.mail),
                        contentDescription = "user",
                        modifier = Modifier.size(20.dp)
                    )
                })
            Spacer(modifier = Modifier.size(20.dp))
            Row {
                Checkbox(
                    colors = CheckboxDefaults.colors(
                        checkedColor = BrightOrange, checkmarkColor = Color.White
                    ),
                    checked = checkedState.value,
                    onCheckedChange = {
                        checkedState.value = it
                        viewModel.onTermsCondition(checkedState.value)
                    },
                )
                Spacer(modifier = Modifier.size(10.dp))
                val annotatedString = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = GrayColor)) {
                        append("I agree to the ")
                    }
                    pushStringAnnotation(tag = "terms", annotation = "")
                    withStyle(style = SpanStyle(color = BrightOrange)) {
                        append("terms & conditions")
                    }
                    pop()
                    withStyle(style = SpanStyle(color = GrayColor)) {
                        append(" \nand ")
                    }
                    pushStringAnnotation(tag = "privacy", annotation = "")
                    withStyle(style = SpanStyle(color = BrightOrange)) {
                        append("privacy policy")
                    }
                    pop()
                }
                ClickableText(text = annotatedString,
                    style = MaterialTheme.typography.body1,
                    onClick = { offset ->
                        annotatedString.getStringAnnotations(
                            tag = "terms", start = offset, end = offset
                        ).firstOrNull()?.let {
                            // navController.navigate("TermsCondition/1")
                        }
                        annotatedString.getStringAnnotations(
                            tag = "privacy", start = offset, end = offset
                        ).firstOrNull()?.let {
                            //navController.navigate("Privacy/2")
                        }
                    })
                Spacer(modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.size(20.dp))
            BasicButton(AppText.create_account, Modifier.basicButton()) {
                viewModel.onSignUpClick(type,appState.activity, openAndPopUp)
            }
            Spacer(modifier = Modifier.size(20.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Already have an account?",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                )
                ClickableText(
                    text = AnnotatedString(" Log In"), onClick = {
                        //  navController.popBackStack()
                    }, style = TextStyle(
                        fontSize = 15.sp,
                        color = BrightOrange,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            if(viewModel.isLoader.value){
                showProgress()
            }
        }
    }
}
