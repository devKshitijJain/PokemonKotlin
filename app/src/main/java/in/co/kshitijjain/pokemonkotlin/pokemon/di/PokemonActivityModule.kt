package `in`.co.kshitijjain.pokemonkotlin.pokemon.di

import `in`.co.kshitijjain.pokemonkotlin.R
import `in`.co.kshitijjain.pokemonkotlin.common.ImageLoader
import `in`.co.kshitijjain.pokemonkotlin.pokemon.*
import `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate.*
import `in`.co.kshitijjain.pokemonkotlin.rx.AndroidSchedulingStrategyFactory
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class PokemonActivityModule {

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
    fun resultViewStateConverter() : ResultViewStateConverter {
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
    internal fun pokemonAdapter(imageLoader: ImageLoader)
            : PokemonAdapter {
        return PokemonAdapter(imageLoader)
    }

    @Provides
    fun pokemonView(pokemonActivity: PokemonActivity, pokemonAdapter: PokemonAdapter) : PokemonView {
        val recyclerView = pokemonActivity.findViewById<RecyclerView>(R.id.pokemon_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(pokemonActivity)
        val pokemonContentView = PokemonContentView(recyclerView, pokemonAdapter)
        val errorText = pokemonActivity.findViewById<TextView>(R.id.pokemon_error_text)
        val progressBar = pokemonActivity.findViewById<ProgressBar>(R.id.pokemon_progressBar)
        return PokemonView.from(pokemonContentView, errorText, progressBar)
    }

    @Provides
    fun pokemonDisplayer(pokemonView: PokemonView) : PokemonDisplayer {
        return PokemonDisplayer(pokemonView)
    }

    @Provides
    fun mainActivityPresenter(pokemonViewStateUseCase: PokemonViewStateUseCase,
                              pokemonDisplayer: PokemonDisplayer): PokemonPresenter {
        return PokemonPresenter(pokemonViewStateUseCase, pokemonDisplayer)
    }
}