package com.example.rickandmortyapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyapp.data.remote.RickAndMortyApiService
import com.example.rickandmortyapp.data.model.Result

class CharacterListVM: ViewModel() {
    private val rickAndMortyApiService = RickAndMortyApiService()
    val characterNameList = MutableLiveData<ArrayList<Result>>()

    suspend fun getNameData() {
        val response = rickAndMortyApiService.getCharacter()
        if (response.isSuccessful){
            val list = arrayListOf<Result>()
            response.body()?.let {
                val name = response.body()?.results
                if (name != null) {
                    for (i in name){
                        list.add(i)
                        Log.d("geldi", i.toString())

                    }
                }
            }
            characterNameList.value = list
        }else{
            Log.d("hata2", "hatalog")
        }


    }
}