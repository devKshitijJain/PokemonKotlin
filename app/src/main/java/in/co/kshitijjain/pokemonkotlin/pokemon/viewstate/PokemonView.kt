package `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate

import `in`.co.kshitijjain.pokemonkotlin.R
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView

class PokemonView(private val pokemonContentView: PokemonContentView,
                  private val errorTextView: TextView,
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
        errorTextView.visibility = View.GONE
    }

    fun showConnectionError() {
        errorTextView.visibility = View.VISIBLE
        errorTextView.setText(R.string.connection_error_occurred)
    }

    fun showServerError() {
        errorTextView.visibility = View.VISIBLE
        errorTextView.setText(R.string.server_error_occurred)
    }

    fun showGenericError() {
        errorTextView.visibility = View.VISIBLE
        errorTextView.setText(R.string.generic_error_occurred)
    }
}
