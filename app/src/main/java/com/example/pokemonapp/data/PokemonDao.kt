package com.example.pokemonapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemonapp.model.PokeResult

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMyPokemon(data: PokeResult)

    @Query("DELETE from tb_pokemon  where name=:idPokemon ")
    fun removePokemon(idPokemon : Int)

    @Query("SELECT * FROM tb_pokemon")
    fun  getMyPokemon() : List<PokeResult>

    @Query("SELECT * FROM tb_pokemon WHERE id = :idPokemon")
    fun  getMyPokemonById(idPokemon: Int) : PokeResult
}