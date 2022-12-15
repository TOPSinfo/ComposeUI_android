package com.tops.composeui.utils
import com.tops.composeui.utils.countrypicker.model.CountryCode

/**
 *
 * Search the country
 *
 */

fun List<CountryCode>.searchCountryList(key: String): MutableList<CountryCode> {
    val tempList = mutableListOf<CountryCode>()
    this.forEach {
        if (it.countryName.lowercase().contains(key.lowercase())) {
            tempList.add(it)
        }
    }
    return tempList
}