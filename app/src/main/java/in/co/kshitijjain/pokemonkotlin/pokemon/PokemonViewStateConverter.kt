package `in`.co.kshitijjain.pokemonkotlin.pokemon

import `in`.co.kshitijjain.pokemonkotlin.rx.Converter

class PokemonViewStateConverter : Converter<Pokemon, PokemonViewState> {
    override fun apply(pokemon: Pokemon): PokemonViewState {
        val resultViewState = ArrayList<ResultViewState>()
        pokemon.mapTo(resultViewState) {
            ResultViewState.create(it.url,
                    it.name,
                    it.url)
        }
        return PokemonViewState.create(resultViewState)
    }
}