package com.astrologer.model.service

/**
 * ConfigurationService implement
 */

interface ConfigurationService {
    fun fetchConfiguration()
    fun getShowTaskEditButtonConfig(): Boolean
}
