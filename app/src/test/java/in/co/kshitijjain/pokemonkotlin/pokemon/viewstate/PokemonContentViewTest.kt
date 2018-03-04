package `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate

import `in`.co.kshitijjain.pokemonkotlin.pokemon.PokemonAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PokemonContentViewTest {

    private val resultViewStates = listOf(ResultViewState.create("name", "url"))

    private val recyclerView: RecyclerView = mock()
    private val pokemonAdapter: PokemonAdapter = mock()

    private lateinit var contentView: PokemonContentView

    @Before
    fun setUp() {
        contentView = PokemonContentView(recyclerView, pokemonAdapter)
    }

    @Test
    fun shouldSetRecyclerViewVisibilityAsVisibleAndSetReceivedViewStatesOnAdapterWhenShowIsCalled() {
        contentView.show(resultViewStates)

        verify(recyclerView).visibility = View.VISIBLE
        verify(pokemonAdapter).setViewStates(resultViewStates)
    }

    @Test
    fun shouldSetRecyclerViewVisibilityAsGoneAndSetEmptyViewStatesOnAdapterWhenHideIsCalled() {
        contentView.hide()

        verify(pokemonAdapter).setViewStates(emptyList())
        verify(recyclerView).visibility = View.GONE
    }
}
