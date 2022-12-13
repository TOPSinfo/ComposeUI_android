package com.astrologer.screens.dashboard.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.astrologer.theme.*

data class ReportModel(
    val date: String,
    val header: String,
    val time: String,
    val rate: String,
    val status: AnnotatedString,
    val color: Color
)

fun upcomingList(): MutableList<ReportModel>{
    val list = mutableListOf<ReportModel>()
    list.add(
        ReportModel(
            date = "4 \n Dec",
            header = "With Astro Peasant",
            time = "Time:  01:10 pm to 01:30 pm",
            rate = "Rate: 27/min",
            color = BlueColor,
            status = bindStringByStatus("Status:","Approved", BlueColor)
        )
    )

    list.add(
        ReportModel(
            date = "10 \n Dec",
            header = "With Astro Adam",
            time = "Time:  01:10 pm to 01:30 pm",
            rate = "Rate: 23/min",
            color = LightOrange,
            status = bindStringByStatus("Status:","Waiting", LightOrange)
        )
    )

    list.add(
        ReportModel(
            date = "10 \n Dec",
            header = "With Astro Gilchrist",
            time = "Time:  01:10 pm to 01:30 pm",
            rate = "Rate: 22/min",
            color = BrightOrange,
            status = bindStringByStatus("Status:","Rejected", BrightOrange)
        )
    )

    list.add(
        ReportModel(
            date = "12 \n Dec",
            header = "With Astro Ian",
            time = "Time:  01:10 pm to 01:30 pm",
            rate = "Rate: 19/min",
            color = RedColor,
            status = bindStringByStatus("Status:","Deleted", RedColor)
        )
    )

    return list
}

fun onGoingList(): MutableList<ReportModel>{
    val list = mutableListOf<ReportModel>()

    list.add(
        ReportModel(
            date = "30 \n Nov",
            header = "With Astro Botham",
            time = "Time:  01:10 pm to 01:30 pm",
            rate = "Rate: 12/min",
            color = BlueColor,
            status = bindStringByStatus("Status:","Approved", BlueColor)
        )
    )

    return list
}

fun pastList(): MutableList<ReportModel>{
    val list = mutableListOf<ReportModel>()

    list.add(
        ReportModel(
            date = "4 \n Nov",
            header = "With Astro Shane",
            time = "Time:  01:10 pm to 01:30 pm",
            rate = "Rate: 17/min",
            color = GreenColor,
            status = bindStringByStatus("Status:","Completed", BlueColor)
        )
    )

    list.add(
        ReportModel(
            date = "3 \n Nov",
            header = "With Astro Watson",
            time = "Time:  01:10 pm to 01:30 pm",
            rate = "Rate: 29/min",
            color = BrightOrange,
            status = bindStringByStatus("Status:","Rejected", BrightOrange)
        )
    )
    return list
}

fun bindStringByStatus(text1: String, text2: String, color: Color): AnnotatedString{
    return buildAnnotatedString {
        append(text1)
        withStyle(style = SpanStyle(
            color = color
        )){
            append(text2)
        }
    }
}
