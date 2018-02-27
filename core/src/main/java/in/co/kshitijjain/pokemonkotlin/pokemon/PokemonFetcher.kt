package `in`.co.kshitijjain.pokemonkotlin.pokemon

import `in`.co.kshitijjain.pokemonkotlin.pokemon.model.Pokemon
import com.squareup.moshi.Moshi
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class PokemonFetcher(
        private val converter: PokemonConverter,
        private val backend: PokemonBackend) {

    companion object {
        fun from(retrofit: Retrofit,
                 moshi: Moshi,
                 pokemonConverter: PokemonConverter): PokemonFetcher {
            val pokemonBackend = retrofit
                    .newBuilder()
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()
                    .create<PokemonBackend>(PokemonBackend::class.java)
            return PokemonFetcher(pokemonConverter, pokemonBackend)
        }
    }

    fun loadFromNetwork(limit: Int): Single<Pokemon> {
        return backend
                .getPokemon(limit)
                .map(converter)
    }
}