package `in`.co.kshitijjain.pokemonkotlin.pokemon

import `in`.co.kshitijjain.pokemonkotlin.rx.AndroidSchedulingStrategyFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


@Module
class PokemonActivityModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://pokeapi.co/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    fun moshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    fun pokemonConverter() : PokemonConverter {
        return PokemonConverter()
    }

    @Provides
    fun pokemonFetcher(
            retrofit: Retrofit,
            moshi: Moshi,
            pokemonConverter: PokemonConverter): PokemonFetcher {
        return PokemonFetcher.from(retrofit, moshi, pokemonConverter)
    }

    @Provides
    fun resultViewStateConverter() : ResultViewStateConverter{
        return ResultViewStateConverter()
    }

    @Provides
    fun pokemonViewStateConverter(pokemonResultViewStateConverter: ResultViewStateConverter)
            : PokemonViewStateConverter {
        return PokemonViewStateConverter(pokemonResultViewStateConverter)
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