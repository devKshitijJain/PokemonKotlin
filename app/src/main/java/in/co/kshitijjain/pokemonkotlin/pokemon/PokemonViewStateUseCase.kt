package `in`.co.kshitijjain.pokemonkotlin.pokemon

import `in`.co.kshitijjain.pokemonkotlin.rx.AndroidSchedulingStrategyFactory
import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.Observable
import io.reactivex.functions.Function
import io.reactivex.subjects.BehaviorSubject

class PokemonViewStateUseCase(
        private val pokemonFetcher: PokemonFetcher,
        private val viewStateConverter: PokemonViewStateConverter,
        private val schedulingStrategyFactory: AndroidSchedulingStrategyFactory) {

    private val repository = BehaviorSubject.create<PokemonViewState>()

    private val saveViewState = Function<PokemonViewState, CompletableSource> { pokemonViewState ->
        Completable.fromAction {
            repository.onNext(pokemonViewState)
        }
    }

    fun observeViewState(): Observable<PokemonViewState> {
        return repository
                .compose(schedulingStrategyFactory.create())
    }

    fun loadFromNetwork(): Completable {
        val loading = PokemonViewState.empty().toLoading()
        return pokemonFetcher.loadFromNetwork(20)
                .toObservable()
                .map(viewStateConverter)
                .startWith(loading)
                .compose(PokemonViewStateErrorConverter(Observable.just(loading)))
                .flatMapCompletable(saveViewState)
                .compose(schedulingStrategyFactory.create<PokemonViewState>())
    }
}