package com.example.rickandmortyapp.data.remote

import com.example.rickandmortyapp.data.model.RickAndMortyDataModel
import com.example.rickandmortyapp.util.Constants.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RickAndMortyApiService {

    private val rickAndMortyApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RickAndMortyApi::class.java)

    suspend fun getCharacter(): Response<RickAndMortyDataModel> {
        return rickAndMortyApi.getCharacterName()
    }
}