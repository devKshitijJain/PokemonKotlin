package `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate

import `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate.PokemonViewState.Error.Type.*
import android.util.Log

class PokemonDisplayer(private val pokemonView: PokemonView) {
    fun display(pokemonViewState: PokemonViewState?) {
        pokemonViewState?.accept(object : PokemonViewState.Visitor {
            override fun visit(idle: PokemonViewState.Idle) {
                pokemonView.hideLoading()
                pokemonView.showContent(idle.results())
            }

            override fun visit(loading: PokemonViewState.Loading) {
                pokemonView.hideContent()
                pokemonView.hideError()
                pokemonView.showLoading()
            }

            override fun visit(error: PokemonViewState.Error) {
                pokemonView.hideLoading()
                showError(error)
                Log.d("ERROR --> ", "Showing error state due to an exception: " + error.cause())
            }
        })
    }

    private fun showError(error: PokemonViewState.Error) {
        pokemonView.hideContent()
        when (error.type()) {
            CONNECTION -> pokemonView.showConnectionError()
            SERVER -> pokemonView.showServerError()
            UNKNOWN -> pokemonView.showGenericError()
        }
    }
}