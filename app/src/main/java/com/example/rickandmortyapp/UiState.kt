package com.example.rickandmortyapp

import com.example.rickandmortyapp.data.model.Character

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: List<Character>) : UiState()
    data class SuccessId(val dataId: Character) : UiState()
}

