package com.example.pokemonapp.ui.pokeinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokemonapp.R
import com.example.pokemonapp.data.PokemonDao
import com.example.pokemonapp.model.PokeResult
import com.example.pokemonapp.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_poke_info.*

class PokeInfoActivity : AppCompatActivity(){

    private var data: PokeResult? = null
    lateinit var viewModel: PokeInfoViewModel

    private var id : Int? = null

    private lateinit var btnmypokemon: ImageButton
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poke_info)

        val factory = ViewModelFactory.getInstance(this.application)
        viewModel = ViewModelProvider(this, factory).get(PokeInfoViewModel::class.java)

        btnmypokemon = findViewById(R.id.btn_mypokemon)

        initUI()

        btnmypokemon.setOnClickListener {
            if (data?.id != null) {
                viewModel.removeMyPokemon(data?.id!!)
                Toast.makeText(this, "Gagal menambah kan ${data?.name}", Toast.LENGTH_SHORT).show()
                favoriteState(false)
            } else {
                viewModel.saveMyPokemon(data!!)
                Toast.makeText(this, "Berhasil menambah kan ${data?.name}", Toast.LENGTH_SHORT).show()
                favoriteState(true)
            }
        }
    }

    private fun initUI(){
        val name = intent.extras?.get("name") as String
        val urlImage = intent.extras?.get("url") as String
        if (intent.extras?.get("id") != null) {
            id = intent.extras?.get("id") as Int
        }

        viewModel.getMyPokemon(id)

        viewModel.pokemonById.observe(this, Observer {
            if (it != null) {
                nameTextView.text = it.name
                heightText.text = "Altura: ${it.height/10.0}m"
                weightText.text = "Peso: ${it.weight/10.0}"
                Glide.with(this).load(it.url).into(imageView)
                id = it.id
                favoriteState(status = true)
            } else {
                viewModel.getPokemonInfo(name)
            }
        })

        viewModel.pokemonInfo.observe(this, Observer { pokemon ->
            if (pokemon != null) {
                data = PokeResult(name = pokemon.name, url = urlImage, weight = pokemon.height, height = pokemon.height)
                nameTextView.text = pokemon.name
                heightText.text = "Altura: ${pokemon.height/10.0}m"
                weightText.text = "Peso: ${pokemon.weight/10.0}"
                Glide.with(this).load(pokemon.sprites.frontDefault).into(imageView)
                favoriteState(status = false)
            }
        })
    }


    private fun favoriteState(status: Boolean) {
        if (status) {
            btnmypokemon.background = ContextCompat.getDrawable(this, R.drawable.ic_baseline_star_blue)
        } else {
            btnmypokemon.background = ContextCompat.getDrawable(this, R.drawable.ic_baseline_star)
        }
    }

//    override fun onClick(v: View?) {
//        when(v?.id){
//            R.id.btn_mypokemon ->{
//                if (btn_mypokemon.background.constantState == ContextCompat.getDrawable(
//                        this,
//                        R.drawable.ic_baseline_star
//                    )!!.constantState
//                ) {
//                    if (data != null) {
//                        viewModel.saveMyPokemon(data!!)
//                        Toast.makeText(this, "Berhasil menambah kan ${data?.name}", Toast.LENGTH_SHORT).show()
//                    } else {
//                        Toast.makeText(this, "Berhasil menambah kan ${data?.name}", Toast.LENGTH_SHORT).show()
//
//                    }
//                } else {
//                    viewModel.removeMyPokemon(data?.id!!)
//                    Toast.makeText(this, "Gagal menambah kan ${data?.name}", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }

}