package com.example.rickandmortyapp.viewModel

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyapp.data.model.Result
import com.example.rickandmortyapp.data.remote.RickAndMortyApiService
import kotlinx.coroutines.launch

class CharacterListVM : ViewModel() {
    private val rickAndMortyApiService = RickAndMortyApiService()
    val characterNameList = MutableLiveData<ArrayList<Result>>()

    init {
        getNameData()
    }

     fun getNameData() {
        viewModelScope.launch {
            val response = rickAndMortyApiService.getCharacter()
            if (response.isSuccessful) {
                val list = arrayListOf<Result>()
                response.body()?.let {
                    val name = response.body()?.results
                    if (name != null) {
                        for (i in name) {
                            list.add(i)
                        }
                    }
                }
                characterNameList.value = list
            }
        }
    }
}