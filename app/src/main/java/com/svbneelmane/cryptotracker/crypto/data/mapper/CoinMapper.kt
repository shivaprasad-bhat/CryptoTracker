package com.svbneelmane.cryptotracker.crypto.data.mapper

import com.svbneelmane.cryptotracker.crypto.data.networking.dto.CoinDto
import com.svbneelmane.cryptotracker.crypto.data.networking.dto.CoinPriceDto
import com.svbneelmane.cryptotracker.crypto.domain.Coin
import com.svbneelmane.cryptotracker.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

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

fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
        priceUsd = priceUsd,
        dateTime = Instant.ofEpochMilli(
            time
        ).atZone(ZoneId.of("UTC"))
    )
}