package `in`.co.kshitijjain.pokemonkotlin.common

import `in`.co.kshitijjain.pokemonkotlin.json.JsonDefaults
import `in`.co.kshitijjain.pokemonkotlin.network.NetworkDefaults
import `in`.co.kshitijjain.pokemonkotlin.pokemon.PokemonConverter

open class BaseIntegrationTest {

    companion object {
        private const val BASE_URL = "https://pokeapi.co/"
        internal val moshi = JsonDefaults.moshi()
        internal val retrofit = NetworkDefaults.retrofit()
                .newBuilder()
                .baseUrl(BASE_URL)
                .build()

        internal val pokemonConverter = PokemonConverter()
    }
}