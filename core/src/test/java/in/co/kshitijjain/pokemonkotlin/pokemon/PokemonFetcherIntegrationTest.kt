package `in`.co.kshitijjain.pokemonkotlin.pokemon

import `in`.co.kshitijjain.pokemonkotlin.common.BaseIntegrationTest
import `in`.co.kshitijjain.pokemonkotlin.pokemon.model.Pokemon
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class PokemonFetcherIntegrationTest : BaseIntegrationTest() {

    private lateinit var pokemonFetcher : PokemonFetcher

    @Before
    fun setUp() {
        pokemonFetcher = PokemonFetcher.from(retrofit,
                moshi, pokemonConverter)
    }

    @Test
    fun shouldLoadPokemonFromRemoteServer() {
        val testObserver = TestObserver<Pokemon>()

        pokemonFetcher.loadFromNetwork(20)
                .subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertValueCount(1)
    }
}
