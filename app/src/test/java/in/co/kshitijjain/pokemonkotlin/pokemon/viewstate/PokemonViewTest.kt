package `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate

import `in`.co.kshitijjain.pokemonkotlin.R
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PokemonViewTest {

    private val list = listOf(ResultViewState.create("name", "url"))

    private val contentView: PokemonContentView = mock()
    private val errorTextView: TextView = mock()
    private val progressBar: ProgressBar = mock()

    private lateinit var pokemonView: PokemonView

    @Before
    fun setUp() {
        pokemonView = PokemonView(contentView, errorTextView, progressBar)
    }

    @Test
    fun shouldHideProgressBarWhenHideLoadingIsCalled() {
        pokemonView.hideLoading()

        verify(progressBar).visibility = View.GONE
    }

    @Test
    fun shouldShowProgressBarWhenShowLoadingIsCalled() {
        pokemonView.showLoading()

        verify(progressBar).visibility = View.VISIBLE
    }

    @Test
    fun shouldShowContentWhenShowContentIsCalled() {
        pokemonView.showContent(list)

        verify(contentView).show(list)
    }

    @Test
    fun shouldHideContentWhenHideContentIsCalled() {
        pokemonView.hideContent()

        verify(contentView).hide()
    }

    @Test
    fun shouldHideErrorWhenHideErrorIsCalled() {
        pokemonView.hideError()

        verify(errorTextView).visibility = View.GONE
    }

    @Test
    fun shouldSetConnectionErrorWhenShowConnectionErrorIsCalled() {
        pokemonView.showConnectionError()

        verify(errorTextView).visibility = View.VISIBLE
        verify(errorTextView).setText(R.string.connection_error_occurred)
    }

    @Test
    fun shouldSetServerErrorWhenShowServerErrorIsCalled() {
        pokemonView.showServerError()

        verify(errorTextView).visibility = View.VISIBLE
        verify(errorTextView).setText(R.string.server_error_occurred)
    }

    @Test
    fun shouldSetGenericErrorWhenShowGenericErrorIsCalled() {
        pokemonView.showGenericError()

        verify(errorTextView).visibility = View.VISIBLE
        verify(errorTextView).setText(R.string.generic_error_occurred)
    }
}
