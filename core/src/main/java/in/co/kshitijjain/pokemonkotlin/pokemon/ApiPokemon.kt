package `in`.co.kshitijjain.pokemonkotlin.pokemon

data class ApiPokemon(
        val count: Int,
        val previous: Any,
        val results: List<Result>,
        val next: Any
)

data class Result(
        private val url: String,
        private val name: String
)