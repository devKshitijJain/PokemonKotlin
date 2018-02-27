package `in`.co.kshitijjain.pokemonkotlin.pokemon

import `in`.co.kshitijjain.pokemonkotlin.R
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var pokemonImage: ImageView = itemView.findViewById(R.id.pokemon_image)
    var pokemonName: TextView = itemView.findViewById(R.id.pokemon_name)
}
