package com.example.pokemonapp.ui.pokelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonapp.model.PokeResult
import com.example.pokemonapp.model.PokemonApiResponse
import com.example.pokemonapp.service.PokeApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeListViewModel() : ViewModel()  {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: PokeApiService = retrofit.create(PokeApiService::class.java)

    val pokemonList = MutableLiveData<List<PokeResult>>()

    fun getPokemonList(){
        val call = service.getPokemonList(100, 0)
        call.enqueue(object : Callback<PokemonApiResponse>{
            override fun onResponse(
                call: Call<PokemonApiResponse>,
                response: Response<PokemonApiResponse>
            ) {
                response.body()?.results.let{
                    pokemonList.postValue(it)
                }
            }

            override fun onFailure(call: Call<PokemonApiResponse>, t: Throwable) {
                t.localizedMessage
            }

        })
    }

}