package com.tops.composeui.model

import java.sql.Timestamp


/**
 * Signup data classes
 * */

data class SignupVO(
    val birthdate: String = "",
    val birthplace: String = "",
    val birthtime: String = "",

    val createdat: Timestamp? = null,
    val lastupdatetime: Timestamp? = null,

    val devicedetails: String = "",
    val email: String = "",
    val fullname: String = "",
    val imagepath: String = "",

    val isOnline: Boolean = false,
    val phone: String = "",
    val profileimage: String = "",
    val socialid: String = "",
    val socialtype: String = "",
    val token: String = "",
    var uid: String = "",
    val usertype: String = "",
    val walletbalance: Int = 0,
    val verificationID: String = "",
    val isLogin: Boolean = false
    // birthdate,birthplace,birthtime,createdat
    // ,devicedetails,email,fullname,imagepath,isOnline,
    // lastupdatetime,phone,profileimage,socialid,socialtype,token,uid,usertype,walletbalance
)
