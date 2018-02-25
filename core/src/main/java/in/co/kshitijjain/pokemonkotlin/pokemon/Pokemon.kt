package `in`.co.kshitijjain.pokemonkotlin.pokemon

data class Pokemon(private val results: List<Result>) : Iterable<Result> {

    override fun iterator(): Iterator<Result> {
        return results.iterator()
    }

    companion object {
        fun create(results: List<Result>): Pokemon {
            return Pokemon(results)
        }
    }
}