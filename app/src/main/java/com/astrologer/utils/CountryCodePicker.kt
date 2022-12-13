package com.astrologer.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.astrologer.R
import com.astrologer.screens.sign_up.SignUpViewModel
import com.astrologer.utils.countrypicker.model.CountryCode
import com.astrologer.utils.countrypicker.model.getFlagMasterResID
import com.astrologer.utils.countrypicker.model.getListCountries

class CountryCodePicker {
    @Composable
    fun CountryCodeDialog(
        viewModel: SignUpViewModel = hiltViewModel(),
        modifier: Modifier = Modifier,
        isOnlyFlagShow: Boolean = false,
        defaultSelectedCountry: CountryCode = getListCountries().first(),
        pickedCountry: (CountryCode) -> Unit,
        dialogSearch: Boolean = true,
        dialogRounded: Int = 4
    ) {
        var mobileState by remember {
            mutableStateOf("")
        }
        val countryList: List<CountryCode> = getListCountries()
        var isPickCountry by remember { mutableStateOf(defaultSelectedCountry) }
        var isOpenDialog by remember { mutableStateOf(false) }
        var searchValue by remember { mutableStateOf("") }
        Card(
            modifier = modifier
                .padding(1.dp)
                .clickable { isOpenDialog = true }
        ) {
            Column(modifier = Modifier.padding(4.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.mobile),
                        contentDescription = "g+",
                        modifier = Modifier
                            .size(20.dp)
                    )
                    if (!isOnlyFlagShow) {
                        Text(
                            isPickCountry.countryPhoneCode,
                            Modifier.padding(horizontal = 14.dp)
                        )
                    }
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = mobileState,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            //cursorColor = Color.Black,
                            disabledLabelColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        onValueChange = { newText ->
                            mobileState = newText
                            viewModel.onMobileChange(mobileState)
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = { Text("Mobile Number") },
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                    )
                }
            }

            //Dialog
            if (isOpenDialog) {
                Dialog(
                    onDismissRequest = { isOpenDialog = false },
                ) {
                    Card(
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.85f),
                        elevation = 4.dp,
                        shape = RoundedCornerShape(dialogRounded.dp)
                    ) {
                        Column {
                            if (dialogSearch) {
                                searchValue = dialogSearchView()
                            }
                            LazyColumn {
                                items(
                                    (if (searchValue.isEmpty()) {
                                        countryList
                                    } else {
                                        countryList.searchCountryList(searchValue)
                                    })
                                ) { countryItem ->
                                    Row(
                                        Modifier
                                            .padding(
                                                horizontal = 18.dp,
                                                vertical = 18.dp
                                            ).fillMaxWidth()
                                            .clickable {
                                                pickedCountry(countryItem)
                                                isPickCountry = countryItem
                                                isOpenDialog = false
                                            },
                                                verticalAlignment = Alignment.CenterVertically,) {
                                        Image(
                                            modifier = Modifier.size(30.dp),
                                            painter = painterResource(
                                                id = getFlagMasterResID(
                                                    countryItem.countryCode
                                                )
                                            ), contentDescription = null
                                        )
                                        Text(
                                            countryItem.countryName,
                                            Modifier.padding(8.dp,0.dp,4.dp,0.dp) .weight(1f),
                                        )
                                        Text(
                                            countryItem.countryPhoneCode,
                                            modifier = Modifier
                                                .padding(4.dp),
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun dialogSearchView(): String {
        var searchValue by remember { mutableStateOf("") }
        Row {
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                value = searchValue,
                onValueChange = {
                    searchValue = it
                },
                fontSize = 14.sp,
                hint = "Search ...",
                textAlign = TextAlign.Start,
            )
        }
        return searchValue
    }

    @Composable
    private fun CustomTextField(
        modifier: Modifier = Modifier,
        value: String,
        onValueChange: (String) -> Unit,
        hint: String = "",
        fontSize: TextUnit = 14.sp,
        textAlign: TextAlign = TextAlign.Center
    ) {
        Box(
            modifier = modifier
                .background(
                    color = Color.White.copy(alpha = 0.1f)
                )
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = value,
                onValueChange = onValueChange,
                textStyle = LocalTextStyle.current.copy(
                    textAlign = textAlign,
                    fontSize = fontSize
                ),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            if (value.isEmpty()) {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray,
                    modifier = Modifier.then(
                        Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 52.dp)
                    )
                )
            }
        }
    }
}