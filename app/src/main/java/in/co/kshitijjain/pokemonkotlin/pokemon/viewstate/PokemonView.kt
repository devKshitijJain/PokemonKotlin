package `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate

import `in`.co.kshitijjain.pokemonkotlin.R
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView

class PokemonView(private val pokemonContentView: PokemonContentView,
                  private val errorText: TextView,
                  private val progressBar: ProgressBar) {

    companion object {
        fun from(pokemonContentView: PokemonContentView,
                 errorText: TextView,
                 progressBar: ProgressBar): PokemonView {
            return PokemonView(pokemonContentView, errorText, progressBar)
        }
    }

    fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    fun showContent(results: List<ResultViewState>) {
        pokemonContentView.show(results)
    }

    fun hideContent() {
        pokemonContentView.hide()
    }

    fun hideError() {
        errorText.visibility = View.GONE
    }

    fun showConnectionError() {
        errorText.visibility = View.VISIBLE
        errorText.text = errorText.context.getString(R.string.connection_error_occurred)
    }

    fun showServerError() {
        errorText.visibility = View.VISIBLE
        errorText.text = progressBar.context.getString(R.string.server_error_occurred)
    }

    fun showGenericError() {
        errorText.visibility = View.VISIBLE
        errorText.text = progressBar.context.getString(R.string.generic_error_occurred)
    }
}