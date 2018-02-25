package `in`.co.kshitijjain.pokemonkotlin.pokemon

import `in`.co.kshitijjain.pokemonkotlin.rx.Converter

class PokemonConverter : Converter<ApiPokemon, Pokemon> {
    override fun apply(apiPokemon: ApiPokemon): Pokemon {
        val results = ArrayList<Result>()
        apiPokemon.mapTo(results) { apiResult -> Result.create(apiResult.url, apiResult.name) }
        return Pokemon.create(results)
    }
}