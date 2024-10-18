package com.svbneelmane.cryptotracker.crypto.data.networking

import com.svbneelmane.cryptotracker.core.data.networking.constructUrl
import com.svbneelmane.cryptotracker.core.data.networking.safeCall
import com.svbneelmane.cryptotracker.core.domain.util.NetworkError
import com.svbneelmane.cryptotracker.core.domain.util.Result
import com.svbneelmane.cryptotracker.core.domain.util.map
import com.svbneelmane.cryptotracker.crypto.data.mapper.toCoin
import com.svbneelmane.cryptotracker.crypto.data.networking.dto.CoinsResponseDto
import com.svbneelmane.cryptotracker.crypto.domain.Coin
import com.svbneelmane.cryptotracker.crypto.domain.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource(
    private val httpClient: HttpClient
) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }

}