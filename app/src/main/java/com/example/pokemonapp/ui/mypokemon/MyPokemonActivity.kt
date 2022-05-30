package com.example.pokemonapp.ui.mypokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.model.PokeResult
import com.example.pokemonapp.ui.pokeinfo.PokeInfoActivity
import com.example.pokemonapp.ui.pokeinfo.PokeInfoViewModel
import com.example.pokemonapp.ui.pokelist.PokeListAdapter
import com.example.pokemonapp.ui.pokelist.PokeListViewModel
import com.example.pokemonapp.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_my_pokemon.*
import kotlinx.android.synthetic.main.activity_pokelist.*

class MyPokemonActivity : AppCompatActivity() {

    private lateinit var viewModel: MyPokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_pokemon)

        val factory = ViewModelFactory.getInstance(this.application)
        viewModel = ViewModelProvider(this, factory).get(MyPokemonViewModel::class.java)

        viewModel.getMyPokemon()

        initUI()
    }

    private fun initUI() {

        val rvPoke = findViewById<RecyclerView>(R.id.mypokemonRecyclerView)

        viewModel.pokemonList.observe(this, Observer { list->
            val myPokemonAdapter = MyPokemonAdapter {
                val intent = Intent(this, PokeInfoActivity::class.java)
                intent.putExtra("name", it.name)
                intent.putExtra("url", it.url)
                intent.putExtra("id", it.id)
                startActivity(intent)
            }
            myPokemonAdapter.setData(list)
            rvPoke.adapter = myPokemonAdapter
            rvPoke.layoutManager = LinearLayoutManager(this)
        })
    }
}