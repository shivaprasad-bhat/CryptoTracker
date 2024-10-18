package com.svbneelmane.cryptotracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.svbneelmane.cryptotracker.core.presentation.util.ObserveAsEvent
import com.svbneelmane.cryptotracker.core.presentation.util.toString
import com.svbneelmane.cryptotracker.crypto.presentation.coin_list.CoinListEvent
import com.svbneelmane.cryptotracker.crypto.presentation.coin_list.CoinListScreen
import com.svbneelmane.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import com.svbneelmane.cryptotracker.ui.theme.CryptoTrackerTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = koinViewModel<CoinListViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    val context = LocalContext.current

                    ObserveAsEvent(events = viewModel.events) { event ->
                        when (event) {
                            is CoinListEvent.Error -> Toast.makeText(
                                context,
                                event.error.toString(context),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    CoinListScreen(state = state, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
