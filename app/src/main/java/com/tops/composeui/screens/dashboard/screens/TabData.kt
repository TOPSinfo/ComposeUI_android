package com.tops.composeui.screens.dashboard.screens

data class TabData(
    val tabName: String
)

fun tabDataList(): MutableList<TabData> {
    val list = mutableListOf<TabData>()
    list.add(
        TabData("Upcoming")
    )
    list.add(
        TabData("Ongoing")
    )
    list.add(
        TabData("Past")
    )
    return list
}
