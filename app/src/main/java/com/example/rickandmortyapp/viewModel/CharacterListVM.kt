package com.example.rickandmortyapp.viewModel

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyapp.UiState
import com.example.rickandmortyapp.data.remote.RickAndMortyApiService
import kotlinx.coroutines.launch

class CharacterListVM : ViewModel() {
    private val rickAndMortyApiService = RickAndMortyApiService()
    val uiState = MutableLiveData<UiState>(UiState.Loading)

    init {
        getCharacterList()
    }
     private fun getCharacterList() {
        viewModelScope.launch() {
            val response = rickAndMortyApiService.getCharacter()
            if (response.isSuccessful) {
                response.body()?.let {
                    uiState.value = UiState.Success(it.characters)
                }
            }
        }
    }
}


