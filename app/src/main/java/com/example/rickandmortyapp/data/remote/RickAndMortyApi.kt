package com.example.rickandmortyapp.data.remote

import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.data.model.RickAndMortyDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {
    @GET("character")
    suspend fun getCharacterName(): Response<RickAndMortyDataModel>

    @GET("character/{id}")
    suspend fun getCharacterWithId(@Path("id")id:Int): Response<Character>
}