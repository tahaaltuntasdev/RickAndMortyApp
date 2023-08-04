package com.example.rickandmortyapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class RickAndMortyDataModel(
    val results: List<Result>
)


data class Result(
    val name: String
)

