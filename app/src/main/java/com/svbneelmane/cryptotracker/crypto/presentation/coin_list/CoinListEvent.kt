package com.svbneelmane.cryptotracker.crypto.presentation.coin_list

import com.svbneelmane.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError) : CoinListEvent
}