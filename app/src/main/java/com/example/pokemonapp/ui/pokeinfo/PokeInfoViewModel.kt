package com.example.pokemonapp.ui.pokeinfo

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonapp.data.AppDatabase
import com.example.pokemonapp.data.PokemonDao
import com.example.pokemonapp.model.PokeResult
import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.service.PokeApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PokeInfoViewModel(application: Application) : ViewModel() {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: PokeApiService = retrofit.create(PokeApiService::class.java)
    private val pokemonDao: PokemonDao
    val pokemonInfo = MutableLiveData<Pokemon>()
    val pokemonById = MutableLiveData<PokeResult>()
    val save = MutableLiveData<PokeResult>()

    init {
        val db = AppDatabase.getInstance(application)
        pokemonDao = db.PokemonDao()
    }

    fun saveMyPokemon(data : PokeResult){
        pokemonDao.saveMyPokemon(data)

    }

    fun removeMyPokemon(id : Int){
        pokemonDao.removePokemon(id)
    }

    fun getPokemonInfo(name: String){
        val call = service.getPokemonInfo(name)

        call.enqueue(object : Callback<Pokemon>{
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                response.body()?.let { pokemon ->
                    pokemonInfo.postValue(pokemon)
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                call.cancel()
            }

        })
    }

    fun getMyPokemon(id: Int?) {
        val data = id?.let { pokemonDao.getMyPokemonById(it) }

        pokemonById.postValue(data)
    }
}
