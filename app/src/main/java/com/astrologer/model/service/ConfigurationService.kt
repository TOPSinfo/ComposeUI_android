package com.astrologer.model.service

interface ConfigurationService {
    fun fetchConfiguration()
    fun getShowTaskEditButtonConfig(): Boolean
}
