package com.example.pokemonapp.ui.mypokemon

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonapp.data.AppDatabase
import com.example.pokemonapp.data.PokemonDao
import com.example.pokemonapp.model.PokeResult
import com.example.pokemonapp.model.PokemonApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPokemonViewModel(application: Application) : ViewModel() {

    private val pokemonDao : PokemonDao

    val pokemonList = MutableLiveData<List<PokeResult>>()

    init {
        val db = AppDatabase.getInstance(application)
        pokemonDao = db.PokemonDao()
    }

    fun getMyPokemon(){
        val data = pokemonDao.getMyPokemon()
        pokemonList.postValue(data)
    }
}