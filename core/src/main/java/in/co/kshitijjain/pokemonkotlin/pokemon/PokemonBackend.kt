package `in`.co.kshitijjain.pokemonkotlin.pokemon

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonBackend {

    @GET("/api/v2/pokemon/")
    abstract fun getPokemon(@Query("limit") limit: Int): Single<ApiPokemon>
}