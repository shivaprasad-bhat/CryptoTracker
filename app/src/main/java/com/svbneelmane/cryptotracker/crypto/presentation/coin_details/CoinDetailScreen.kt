package com.svbneelmane.cryptotracker.crypto.presentation.coin_details

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.svbneelmane.cryptotracker.R
import com.svbneelmane.cryptotracker.crypto.presentation.coin_details.components.InfoCard
import com.svbneelmane.cryptotracker.crypto.presentation.coin_list.CoinState
import com.svbneelmane.cryptotracker.crypto.presentation.coin_list.components.previewCoin
import com.svbneelmane.cryptotracker.crypto.presentation.models.toCoinUi
import com.svbneelmane.cryptotracker.crypto.presentation.models.toDisplayableNumber
import com.svbneelmane.cryptotracker.ui.theme.CryptoTrackerTheme
import com.svbneelmane.cryptotracker.ui.theme.greenBackground

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailScreen(
    state: CoinState,
    modifier: Modifier = Modifier
) {
    val contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    if (state.isLoading) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (state.selectedCoin != null) {
        val coin = state.selectedCoin
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = coin.iconRes),
                contentDescription = coin.name,
                modifier = Modifier.size(100.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = coin.name,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                color = contentColor

            )
            Text(
                text = coin.symbol,
                fontWeight = FontWeight.Light,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = contentColor

            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                InfoCard(
                    title = stringResource(id = R.string.market_cap),
                    formattedText = "$ ${coin.marketCapUsd.formatted}",
                    icon = ImageVector.vectorResource(
                        id = R.drawable.stock
                    )
                )
                InfoCard(
                    title = stringResource(id = R.string.price),
                    formattedText = "$ ${coin.price.formatted}",
                    icon = ImageVector.vectorResource(
                        id = R.drawable.dollar
                    )
                )
                val absoluteChangeFormatted =
                    (coin.price.value * (coin.changePercent24Hr.value / 100)).toDisplayableNumber()

                val isPositive = coin.changePercent24Hr.value > 0.0

                val changeCardContentColor = if (isPositive) {
                    if (isSystemInDarkTheme()) Color.Green else greenBackground
                } else {
                    MaterialTheme.colorScheme.error
                }

                InfoCard(
                    title = stringResource(id = R.string.change_last_24_hours),
                    formattedText = absoluteChangeFormatted.formatted,
                    icon = if (isPositive) ImageVector.vectorResource(
                        id = R.drawable.trending
                    ) else ImageVector.vectorResource(id = R.drawable.trending_down),
                    contentColor = changeCardContentColor
                )
            }


        }
    }
}

@Preview
@PreviewLightDark
@Composable
fun PreviewCoinDetails() {
    CryptoTrackerTheme {
        CoinDetailScreen(
            state = CoinState(
                selectedCoin = previewCoin.toCoinUi()
            ),
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}