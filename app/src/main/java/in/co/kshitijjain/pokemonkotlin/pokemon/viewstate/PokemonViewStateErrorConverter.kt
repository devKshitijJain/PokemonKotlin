package `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate

import com.squareup.moshi.JsonEncodingException
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.functions.Function
import retrofit2.HttpException
import java.io.IOException

class PokemonViewStateErrorConverter(private val cachedViewStateObservable
                                     : Observable<PokemonViewState>)
    : ObservableTransformer<PokemonViewState, PokemonViewState> {

    override fun apply(upstream: Observable<PokemonViewState>): ObservableSource<PokemonViewState> {
        return upstream.onErrorResumeNext(Function<Throwable,
                ObservableSource<PokemonViewState>> { throwable ->
            cachedViewStateObservable.map<PokemonViewState>(toError(throwable))
        })
    }

    private fun toError(cause: Throwable): Function<PokemonViewState, PokemonViewState> {
        return Function { pokemonViewState ->
            convertToError(pokemonViewState, cause)
        }
    }

    private fun convertToError(viewState: PokemonViewState, cause: Throwable): PokemonViewState {
        if (cause is HttpException) {
            val httpErrorCode = cause.code()
            if (isServerError(httpErrorCode)) {
                return viewState.toError(PokemonViewState.Error.Type.SERVER, cause)
            }
        }
        if (cause is JsonEncodingException) {
            return viewState.toError(PokemonViewState.Error.Type.UNKNOWN, cause)
        }
        return if (cause is IOException) {
            viewState.toError(PokemonViewState.Error.Type.CONNECTION, cause)
        } else viewState.toError(PokemonViewState.Error.Type.UNKNOWN, cause)
    }

    private fun isServerError(code: Int): Boolean {
        return code >= HTTP_SERVER_ERROR_CODE
    }

    companion object {

        private const val HTTP_SERVER_ERROR_CODE = 500
    }
}