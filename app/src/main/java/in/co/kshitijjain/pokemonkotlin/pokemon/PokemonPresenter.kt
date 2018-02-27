package `in`.co.kshitijjain.pokemonkotlin.pokemon

import `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate.PokemonDisplayer
import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign

class PokemonPresenter(private val useCase: PokemonViewStateUseCase,
                       private val pokemonDisplayer: PokemonDisplayer) {

    private val disposables = CompositeDisposable()

    fun startPresenting() {
        disposables += useCase.observeViewState().subscribe({
            Log.d("Sfjkhdkshfdas", it.toString())
            pokemonDisplayer.display(it)
        })
        disposables += (useCase.loadFromNetwork().subscribe())
    }

    fun stopPresenting() {
        disposables.clear()
    }
}