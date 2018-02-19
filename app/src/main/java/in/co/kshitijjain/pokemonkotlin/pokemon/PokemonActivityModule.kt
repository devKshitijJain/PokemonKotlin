package `in`.co.kshitijjain.pokemonkotlin.pokemon

import `in`.co.kshitijjain.pokemonkotlin.rx.AndroidSchedulingStrategyFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class PokemonActivityModule {

    @Provides
    fun pokemonFetcher(
            retrofit: Retrofit,
            moshi: Moshi,
            pokemonConverter: PokemonConverter): PokemonFetcher {
        return PokemonFetcher.from(retrofit, moshi, pokemonConverter)
    }

    @Provides
    fun pokemonViewStateConverter(): PokemonViewStateConverter {
        return PokemonViewStateConverter()
    }

    @Provides
    fun mainActivityViewStateUseCase(
            pokemonFetcher: PokemonFetcher,
            pokemonViewStateConverter: PokemonViewStateConverter): PokemonViewStateUseCase {
        val schedulingStrategyFactory = AndroidSchedulingStrategyFactory.io()
        return PokemonViewStateUseCase(pokemonFetcher,
                pokemonViewStateConverter,
                schedulingStrategyFactory)
    }

    @Provides
    fun mainActivityPresenter(pokemonViewStateUseCase: PokemonViewStateUseCase): PokemonPresenter {
        return PokemonPresenter(pokemonViewStateUseCase)
    }
}