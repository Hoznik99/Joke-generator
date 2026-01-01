package com.example.jokegenerator.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jokegenerator.ui.vm.JokeViewModel
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(vm: JokeViewModel = viewModel()) {
    val state by vm.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Joke Generator", style = MaterialTheme.typography.headlineSmall)

        if (state.savedInfo != null) {
            Text(state.savedInfo!!, style = MaterialTheme.typography.bodyMedium)
        }

        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.error != null -> {
                Text("Chyba: ${state.error}", color = MaterialTheme.colorScheme.error)
                Button(onClick = { vm.loadJoke() }) {
                    Text("Zkusit znovu")
                }
            }

            state.joke != null -> {
                Text(state.joke!!.asDisplayText(), style = MaterialTheme.typography.bodyLarge)

                Button(onClick = { vm.loadJoke() }) {
                    Text("Nový")
                }

                Button(onClick = { vm.saveCurrentJokeText() }) {
                    Text("Uložit")
                }
            }

            else -> {
                Button(onClick = { vm.loadJoke() }) {
                    Text("Vtip")
                }
            }
        }
    }

    if (state.savedInfo != null) {
        LaunchedEffect(state.savedInfo) {
            delay(1200)
            vm.clearSavedInfo()
        }
    }
}
