package com.example.pokemonapp.ui.mypokemon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.model.PokeResult
import kotlinx.android.synthetic.main.card_pokemon_search.view.*

class MyPokemonAdapter(val pokemonClick: (PokeResult) -> Unit): RecyclerView.Adapter<MyPokemonAdapter.SearchViewHolder>() {
    var pokemonList: List<PokeResult> = emptyList<PokeResult>()

    fun setData(list: List<PokeResult>){
        pokemonList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_pokemon_search, parent,false))
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.itemView.pokemonText.text = "${position + 1} - ${pokemon.name}"

        holder.itemView.setOnClickListener { pokemonClick(pokemon) }
    }

    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}