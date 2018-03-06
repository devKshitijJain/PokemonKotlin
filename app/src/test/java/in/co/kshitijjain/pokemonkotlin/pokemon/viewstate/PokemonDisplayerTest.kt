package `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate

import `in`.co.kshitijjain.pokemonkotlin.pokemon.PokemonViewStateFixtures.Companion.aPokemonViewState
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.junit.Before
import org.junit.Test
import `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate.PokemonViewState.Error.Type.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PokemonDisplayerTest {

    private val view: PokemonView = mock()
    private val throwable: Throwable = mock()

    private lateinit var displayer: PokemonDisplayer

    @Before
    fun setUp() {
        displayer = PokemonDisplayer(view)
    }

    @Test
    fun shouldShowContentWhenViewStateIsIdle() {
        val pokemonViewState = aPokemonViewState()
                .withResultViewState(ResultViewState.create("name", "url"))
                .toIdle()

        displayer.display(pokemonViewState)

        verify(view).hideLoading()
        verify(view).showContent(pokemonViewState.results())
        verifyNoMoreInteractions(view)
    }

    @Test
    fun shouldShowLoadingWhenViewStateIsLoading() {
        val pokemonLoadingViewState = aPokemonViewState().toLoading()

        displayer.display(pokemonLoadingViewState)

        verify(view).showLoading()
        verify(view).hideContent()
        verify(view).hideError()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun shouldShowServerErrorViewWhenServerError() {
        val pokemonErrorViewState = aPokemonViewState().toError(SERVER, throwable)

        displayer.display(pokemonErrorViewState)

        verify(view).showServerError()
        verifyErrorViewInvocations(view)
    }

    @Test
    fun shouldShowNoConnectionErrorViewWhenNoConnection() {
        val pokemonErrorViewState = aPokemonViewState().toError(CONNECTION, throwable)

        displayer.display(pokemonErrorViewState)

        verify(view).showConnectionError()
        verifyErrorViewInvocations(view)
    }

    @Test
    fun shouldShowGenericErrorViewWhenServerError() {
        val pokemonErrorViewState = aPokemonViewState().toError(UNKNOWN, throwable)

        displayer.display(pokemonErrorViewState)

        verify(view).showGenericError()
        verifyErrorViewInvocations(view)
    }

    private fun verifyErrorViewInvocations(view: PokemonView) {
        verify(view).hideLoading()
        verify(view).hideContent()
        verifyNoMoreInteractions(view)
    }
}