package `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate

import `in`.co.kshitijjain.pokemonkotlin.pokemon.PokemonViewStateFixtures.Companion.aPokemonViewState
import com.squareup.moshi.JsonEncodingException
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate.PokemonViewState.Error.Type.*

class PokemonViewStateErrorConverterTest {

    private val unknownException = RuntimeException()
    private val ioException = IOException()
    private val httpServerException = createHttpException(500)
    private val jsonEncodingException = JsonEncodingException("")
    private val unknownError = Observable.error<PokemonViewState>(unknownException)
    private val connectionError = Observable.error<PokemonViewState>(ioException)
    private val serverError = Observable.error<PokemonViewState>(httpServerException)
    private val jsonEncodingError = Observable.error<PokemonViewState>(jsonEncodingException)

    private val cachedViewState: PokemonViewState = aPokemonViewState().toIdle()
    private val unknownErrorMappedWithCachedViewState = cachedViewState.toError(UNKNOWN, unknownException)
    private val connectionErrorMappedWithCachedViewState = cachedViewState.toError(CONNECTION, ioException)
    private val serverErrorMappedWithCachedViewState = cachedViewState.toError(SERVER, httpServerException)
    private val jsonErrorMappedWithCachedViewState = cachedViewState.toError(UNKNOWN, jsonEncodingException)

    private lateinit var converter: PokemonViewStateErrorConverter
    private lateinit var testSubscriber: TestObserver<PokemonViewState>

    @Before
    fun setUp() {
        converter = PokemonViewStateErrorConverter(cachedViewState)
        testSubscriber = TestObserver()
    }

    @Test
    fun shouldReturnUnknownErrorViewStateOnUnknownError() {

        converter.apply(unknownError).subscribe(testSubscriber)

        testSubscriber.assertValue(unknownErrorMappedWithCachedViewState)
    }

    @Test
    fun shouldReturnConnectionErrorViewStateOnNoInternetError() {

        converter.apply(connectionError).subscribe(testSubscriber)

        testSubscriber.assertValue(connectionErrorMappedWithCachedViewState)
    }

    @Test
    fun shouldReturnJsonErrorViewStateOnJsonEncodingError() {

        converter.apply(jsonEncodingError).subscribe(testSubscriber)

        testSubscriber.assertValue(jsonErrorMappedWithCachedViewState)
    }

    @Test
    fun shouldReturnHttpErrorViewStateOnServerError() {

        converter.apply(serverError).subscribe(testSubscriber)

        testSubscriber.assertValue(serverErrorMappedWithCachedViewState)
    }

    private fun createHttpException(code: Int): HttpException {
        return HttpException(Response.error<Any>(code,
                ResponseBody.create(MediaType.parse(""), "")))
    }
}
