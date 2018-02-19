package `in`.co.kshitijjain.pokemonkotlin.pokemon

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign

class PokemonPresenter(private val useCase: PokemonViewStateUseCase) {

    private val disposables = CompositeDisposable()

    fun startPresenting() {
        disposables += useCase.observeViewState().subscribe({
            Log.d("HELLO", it.toString())
        })
        disposables += (useCase.loadFromNetwork().subscribe())
    }

    fun stopPresenting() {
        disposables.clear()
    }
}