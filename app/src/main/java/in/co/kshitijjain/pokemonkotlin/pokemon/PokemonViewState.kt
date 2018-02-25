package `in`.co.kshitijjain.pokemonkotlin.pokemon

data class PokemonViewState(val results: List<ResultViewState>) {

    companion object {
        fun create(resultViewState: List<ResultViewState>): PokemonViewState {
            return PokemonViewState(resultViewState)
        }
    }
}