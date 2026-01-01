package com.example.jokegenerator.ui.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokegenerator.data.Network
import com.example.jokegenerator.data.local.DatabaseProvider
import com.example.jokegenerator.data.local.FavouriteJokeEntity
import com.example.jokegenerator.data.remote.JokeDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class JokeUiState(
    val isLoading: Boolean = false,
    val joke: JokeDto? = null,
    val error: String? = null,
    val savedInfo: String? = null
)

class JokeViewModel(app: Application) : AndroidViewModel(app) {

    private val _state = MutableStateFlow(JokeUiState())
    val state: StateFlow<JokeUiState> = _state

    fun loadJoke() {
        viewModelScope.launch {
            _state.value = JokeUiState(isLoading = true)
            try {
                val result = Network.jokeApi.getRandomJoke()
                _state.value = JokeUiState(joke = result)
            } catch (e: Exception) {
                _state.value = JokeUiState(error = e.message ?: "Unknown error")
            }
        }
    }

    fun saveCurrentJokeText() {
        val current = _state.value.joke ?: return
        val text = current.asDisplayText().trim()
        if (text.isBlank()) return

        viewModelScope.launch {
            val db = DatabaseProvider.get(getApplication())
            db.favouriteJokeDao().insert(
                FavouriteJokeEntity(
                    text = text,
                    createdAt = System.currentTimeMillis()
                )
            )
            _state.value = _state.value.copy(savedInfo = "Uloženo")
        }
    }

    fun clearSavedInfo() {
        _state.value = _state.value.copy(savedInfo = null)
    }
}
