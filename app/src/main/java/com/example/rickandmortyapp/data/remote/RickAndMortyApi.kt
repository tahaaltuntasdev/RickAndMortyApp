package com.example.rickandmortyapp.data.remote

import com.example.rickandmortyapp.data.model.RickAndMortyDataModel
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyApi {
    @GET("character")
    suspend fun getCharacterName(): Response<RickAndMortyDataModel>
}