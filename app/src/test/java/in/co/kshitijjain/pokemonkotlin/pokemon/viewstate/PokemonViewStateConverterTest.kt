package `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate

import `in`.co.kshitijjain.pokemonkotlin.pokemon.model.Pokemon
import `in`.co.kshitijjain.pokemonkotlin.pokemon.model.Result
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PokemonViewStateConverterTest {

    private val resultViewStateConverter: ResultViewStateConverter = mock()

    private lateinit var converter: PokemonViewStateConverter
    private val result: Result = Result.create("url", "name")
    private val resultViewState: ResultViewState = ResultViewState.create("name", "url")
    private val expectedPokemonViewState: PokemonViewState = PokemonViewState
            .create(listOf(resultViewState))

    @Before
    fun setUp() {
        converter = PokemonViewStateConverter(resultViewStateConverter)
    }

    @Test
    fun shouldConvertPokemonToPokemonViewState() {
        whenever(resultViewStateConverter.convert(result)).thenReturn(resultViewState)

        val convertedPokemonViewState = converter.apply(Pokemon.create(listOf(result)))

        assertThat(convertedPokemonViewState).isEqualTo(expectedPokemonViewState)
    }
}