package com.example.jokegenerator.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokegenerator.data.Network
import com.example.jokegenerator.data.remote.JokeDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class JokeUiState(
    val isLoading: Boolean = false,
    val joke: JokeDto? = null,
    val error: String? = null
)

class JokeViewModel : ViewModel() {
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

    fun clearJoke() {
        _state.value = JokeUiState()
    }
}
