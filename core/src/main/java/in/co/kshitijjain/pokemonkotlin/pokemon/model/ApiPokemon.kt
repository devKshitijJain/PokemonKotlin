package `in`.co.kshitijjain.pokemonkotlin.pokemon.model

data class ApiPokemon(val count: Int,
                      val previous: String?,
                      private val results: List<ApiResult>,
                      val next: String?) : Iterable<ApiResult> {
    override fun iterator(): Iterator<ApiResult> {
        return results.iterator()
    }
}