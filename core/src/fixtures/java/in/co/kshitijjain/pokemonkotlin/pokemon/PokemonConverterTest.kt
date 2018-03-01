package `in`.co.kshitijjain.pokemonkotlin.pokemon

import `in`.co.kshitijjain.pokemonkotlin.pokemon.model.ApiPokemon
import `in`.co.kshitijjain.pokemonkotlin.pokemon.model.ApiResult
import `in`.co.kshitijjain.pokemonkotlin.pokemon.model.Pokemon
import `in`.co.kshitijjain.pokemonkotlin.pokemon.model.Result
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class PokemonConverterTest {

    private lateinit var converter: PokemonConverter

    @Before
    fun setUp() {
        converter = PokemonConverter()
    }

    @Test
    fun shouldConvertApiPokemonToPokemon() {
        val convertedPokemon = converter.apply(ApiPokemon(20,
                null,
                listOf(ApiResult("url", "name")),
                null))

        assertThat(convertedPokemon).isEqualTo(Pokemon.create(listOf(Result("url", "name"))))
    }
}