package com.example.jokegenerator.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jokegenerator.ui.vm.FavouritesViewModel

@Composable
fun FavouritesScreen(vm: FavouritesViewModel = viewModel()) {
    val favourites by vm.favourites.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Oblíbené", style = MaterialTheme.typography.headlineSmall)

        if (favourites.isEmpty()) {
            Text("Zatím tu nic není.")
        } else {
            Button(onClick = { vm.deleteAll() }) {
                Text("Smazat vše")
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(favourites, key = { it.id }) { item ->
                    Text(
                        text = item.text,
                        modifier = Modifier.clickable { vm.delete(item) },
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
