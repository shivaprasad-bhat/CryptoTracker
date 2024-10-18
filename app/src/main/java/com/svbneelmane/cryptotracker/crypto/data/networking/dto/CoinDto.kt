package com.svbneelmane.cryptotracker.crypto.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
    val id: String,
    val rank: Int,
    val symbol: String,
    val name: String,
    val marketCapUsd: Double,
    val priceUsd: Double,
    val changePercent24Hr: Double
)

@Serializable
data class CoinsResponseDto(
    val data: List<CoinDto>
)