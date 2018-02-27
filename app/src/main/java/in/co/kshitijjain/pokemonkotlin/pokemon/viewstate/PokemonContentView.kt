package `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate

import `in`.co.kshitijjain.pokemonkotlin.pokemon.PokemonAdapter
import android.support.v7.widget.RecyclerView
import android.view.View

class PokemonContentView(private val recyclerView: RecyclerView,
                         private val pokemonAdapter: PokemonAdapter) {

    init {
        recyclerView.adapter = pokemonAdapter
    }

    fun show(results: List<ResultViewState>) {
        recyclerView.visibility = View.VISIBLE
        pokemonAdapter.setViewStates(results)
    }

    fun hide() {
        recyclerView.visibility = View.GONE
    }
}