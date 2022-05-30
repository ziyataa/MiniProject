package com.example.pokemonapp.service

import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.model.PokemonApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon/{id}")
    fun getPokemonInfo(@Path("id") id: String): Call<Pokemon>
    @GET("pokemon")
    fun getPokemonList(@Query("limit") Limit: Int, @Query("offset") offset: Int): Call<PokemonApiResponse>

}