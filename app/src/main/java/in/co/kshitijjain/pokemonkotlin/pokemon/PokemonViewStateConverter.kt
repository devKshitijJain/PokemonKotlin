package `in`.co.kshitijjain.pokemonkotlin.pokemon

import `in`.co.kshitijjain.pokemonkotlin.rx.Converter

class PokemonViewStateConverter(private val pokemonResultViewStateConverter: ResultViewStateConverter)
    : Converter<Pokemon, PokemonViewState> {
    override fun apply(pokemon: Pokemon): PokemonViewState {
        val resultViewState = ArrayList<ResultViewState>()
        pokemon.mapTo(resultViewState) {
            pokemonResultViewStateConverter.convert(it)
        }
        return PokemonViewState.create(resultViewState)
    }
}