package `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate

import `in`.co.kshitijjain.pokemonkotlin.pokemon.model.Result
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ResultViewStateConverterTest {

    private lateinit var converter: ResultViewStateConverter

    @Before
    fun setUp() {
        converter = ResultViewStateConverter()
    }

    @Test
    fun shouldConvertResultToResultViewState() {
        val convertedResultViewState = converter.convert(
                Result.create("http://url/1", "name"))

        assertThat(convertedResultViewState).isEqualTo(
                ResultViewState.create("Name",
                        "http://pokeapi.co/media/sprites/pokemon/1.png"))
    }
}