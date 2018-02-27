package `in`.co.kshitijjain.pokemonkotlin.pokemon

data class ResultViewState(val name :String, val imageUrl :String) {
    companion object {
        fun create(name: String, imageUrl: String): ResultViewState {
            return ResultViewState(name, imageUrl)
        }
    }
}