package `in`.co.kshitijjain.pokemonkotlin.pokemon

import `in`.co.kshitijjain.pokemonkotlin.pokemon.PokemonViewStateFixtures.Companion.aPokemonViewState
import `in`.co.kshitijjain.pokemonkotlin.pokemon.model.Pokemon
import `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate.PokemonViewState
import `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate.PokemonViewState.Error.Type.*
import `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate.PokemonViewStateConverter
import `in`.co.kshitijjain.rx.TestSchedulingStrategyFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.internal.functions.Functions.equalsWith
import io.reactivex.observers.TestObserver
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import com.squareup.moshi.JsonEncodingException

@RunWith(MockitoJUnitRunner::class)
class PokemonViewStateUseCaseTest {

    private val unknownException = RuntimeException()
    private val ioException = IOException()
    private val httpServerException = createHttpException(500)
    private val jsonEncodingException = JsonEncodingException("")
    private val loadingViewState: PokemonViewState = aPokemonViewState().toLoading()
    private val idleViewState = aPokemonViewState().toIdle()
    private val unknownErrorViewState = aPokemonViewState().toError(UNKNOWN, unknownException)
    private val connectionErrorViewState = aPokemonViewState().toError(CONNECTION, ioException)
    private val serverErrorViewState = aPokemonViewState().toError(SERVER, httpServerException)
    private val jsonErrorViewState = aPokemonViewState().toError(UNKNOWN, jsonEncodingException)

    private val pokemonFetcher: PokemonFetcher = mock()
    private val viewStateConverter: PokemonViewStateConverter = mock()
    private val pokemon: Pokemon = mock()

    private lateinit var observer: TestObserver<PokemonViewState>
    private lateinit var useCase: PokemonViewStateUseCase

    @Before
    fun setUp() {
        useCase = PokemonViewStateUseCase(pokemonFetcher, viewStateConverter,
                TestSchedulingStrategyFactory.immediate())
        observer = TestObserver()
        whenever(pokemonFetcher.loadFromNetwork(anyInt())).thenReturn(Single.never())
        whenever(viewStateConverter.apply(pokemon)).thenReturn(idleViewState)
    }

    @Test
    fun shouldReturnLoadingState() {
        useCase.observeViewState().subscribe(observer)

        useCase.loadFromNetwork().subscribe()

        observer.assertValueCount(1)
        observer.assertValue(equalsWith<PokemonViewState>(loadingViewState))
    }

    @Test
    fun shouldReturnConvertedPokemonViewStateAfterPokemonAreFetched() {
        whenever(pokemonFetcher.loadFromNetwork(20)).thenReturn(Single.just(pokemon))
        useCase.observeViewState().subscribe(observer)

        useCase.loadFromNetwork().subscribe()

        observer.assertValueCount(2)
        observer.assertValues(loadingViewState, idleViewState)
    }

    @Test
    fun shouldReturnUnknownErrorStateWhenPokemonFetchingFailsWithUnknownException() {
        whenever(pokemonFetcher.loadFromNetwork(20))
                .thenReturn(Single.error<Pokemon>(unknownException))
        useCase.observeViewState().subscribe(observer)

        useCase.loadFromNetwork().subscribe()

        observer.assertValueCount(2)
        observer.assertValueAt(1, equalsWith<PokemonViewState>(unknownErrorViewState))
    }

    @Test
    fun shouldReturnConnectionErrorStateWhenPokemonFetchingFailsWithIoException() {
        whenever(pokemonFetcher.loadFromNetwork(20))
                .thenReturn(Single.error<Pokemon>(ioException))
        useCase.observeViewState().subscribe(observer)

        useCase.loadFromNetwork().subscribe()

        observer.assertValueCount(2)
        observer.assertValueAt(1, equalsWith<PokemonViewState>(connectionErrorViewState))
    }

    @Test
    fun shouldReturnServerErrorStateWhenPokemonFetchingFailsWithHttpServerException() {
        whenever(pokemonFetcher.loadFromNetwork(20))
                .thenReturn(Single.error<Pokemon>(httpServerException))
        useCase.observeViewState().subscribe(observer)

        useCase.loadFromNetwork().subscribe()

        observer.assertValueCount(2)
        observer.assertValueAt(1, equalsWith<PokemonViewState>(serverErrorViewState))
    }

    @Test
    fun shouldReturnUnknownErrorStateWhenPokemonFetchingFailsWithJsonEncodingException() {
        whenever(pokemonFetcher.loadFromNetwork(20))
                .thenReturn(Single.error<Pokemon>(jsonEncodingException))
        useCase.observeViewState().subscribe(observer)

        useCase.loadFromNetwork().subscribe()

        observer.assertValueCount(2)
        observer.assertValueAt(1, equalsWith<PokemonViewState>(jsonErrorViewState))
    }

    private fun createHttpException(code: Int): HttpException {
        return HttpException(Response.error<Any>(code,
                ResponseBody.create(MediaType.parse(""), "")))
    }
}