package com.astrologer.utils
import com.astrologer.utils.countrypicker.model.CountryCode

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