package com.example.rickandmortyapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.UiState
import com.example.rickandmortyapp.data.remote.RickAndMortyApiService
import kotlinx.coroutines.launch

class CharacterDetailVM:ViewModel() {

    private val rickAndMortyApiService = RickAndMortyApiService()
    val uiState = MutableLiveData<UiState>(UiState.Loading)
    fun getCharacter(id:Int) {
        viewModelScope.launch() {
            val response = rickAndMortyApiService.getCharacterWithId(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    uiState.value = UiState.SuccessId(it)
                }
            }
        }
    }
}