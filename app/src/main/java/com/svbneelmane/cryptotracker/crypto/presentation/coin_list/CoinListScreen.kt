package com.svbneelmane.cryptotracker.crypto.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.svbneelmane.cryptotracker.crypto.presentation.coin_list.components.CoinListItem
import com.svbneelmane.cryptotracker.crypto.presentation.coin_list.components.previewCoin
import com.svbneelmane.cryptotracker.crypto.presentation.models.toCoinUi
import com.svbneelmane.cryptotracker.ui.theme.CryptoTrackerTheme
import timber.log.Timber


const val tag: String = "CoinListScreen.kt::composable"

@Composable
fun CoinListScreen(
    modifier: Modifier = Modifier,
    state: CoinState,
    onAction: (CoinListAction) -> Unit
) {
    if (state.isLoading) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.coins) { coin ->
                CoinListItem(
                    coinUi = coin,
                    onClick = {
                        Timber.d("CoinListScreen: onCLick of Item")
                        onAction(CoinListAction.OnCoinClick(coinUi = coin))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                HorizontalDivider()
            }
        }
    }
}


@Preview
@PreviewLightDark
@PreviewDynamicColors
@Composable
fun PreviewCoinListScreen() {
    CryptoTrackerTheme {
        CoinListScreen(
            state = CoinState(coins = (0..100).map {
                previewCoin.toCoinUi().copy(id = it.toString())
            }),
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            onAction = {}
        )
    }
}