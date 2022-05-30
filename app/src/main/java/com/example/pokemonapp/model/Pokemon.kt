package com.example.pokemonapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pokemon(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val sprites: Sprites
)

data class Sprites(
    @SerializedName("front_default")
    val frontDefault: String?,

    @SerializedName("front_shiny")
    val frontShiny: String?
)