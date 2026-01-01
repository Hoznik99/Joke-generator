package com.example.jokegenerator.ui.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokegenerator.data.local.DatabaseProvider
import com.example.jokegenerator.data.local.FavouriteJokeEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavouritesViewModel(app: Application) : AndroidViewModel(app) {

    private val dao = DatabaseProvider.get(getApplication()).favouriteJokeDao()

    val favourites = dao.getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun delete(item: FavouriteJokeEntity) {
        viewModelScope.launch {
            dao.delete(item)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            dao.deleteAll()
        }
    }
}
