package com.svbneelmane.cryptotracker.crypto.domain

import com.svbneelmane.cryptotracker.core.domain.util.NetworkError
import com.svbneelmane.cryptotracker.core.domain.util.Result

fun interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}