package com.svbneelmane.cryptotracker.crypto.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinPriceDto(
    val priceUsd: Double,
    val time: Long
)

@Serializable
data class CoinHistoryDto(
    val data: List<CoinPriceDto>
)