package `in`.co.kshitijjain.pokemonkotlin.pokemon.adapter

import `in`.co.kshitijjain.pokemonkotlin.R
import `in`.co.kshitijjain.pokemonkotlin.common.ImageLoader
import `in`.co.kshitijjain.pokemonkotlin.pokemon.ResultViewState
import `in`.co.kshitijjain.pokemonkotlin.pokemon.holder.PokemonViewHolder
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import java.util.ArrayList

class PokemonAdapter(private val imageLoader: ImageLoader) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var viewStates = emptyList<ResultViewState>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.pokemon_item, parent, false)
        return PokemonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return viewStates.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val pokemon = viewStates[position]
        (holder as PokemonViewHolder).pokemonName.text = pokemon.name
        imageLoader
                .load(pokemon.imageUrl)
                .withPlaceholder(R.drawable.placeholder)
                .into(holder.pokemonImage)
    }

    fun setViewStates(results: List<ResultViewState>) {
        this.viewStates = ArrayList<ResultViewState>(results)
        notifyDataSetChanged()
    }

}