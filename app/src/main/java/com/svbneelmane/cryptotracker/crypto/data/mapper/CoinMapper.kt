package com.svbneelmane.cryptotracker.crypto.data.mapper

import com.svbneelmane.cryptotracker.crypto.data.networking.dto.CoinDto
import com.svbneelmane.cryptotracker.crypto.domain.Coin

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}