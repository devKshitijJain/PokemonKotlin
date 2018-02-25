package `in`.co.kshitijjain.pokemonkotlin.pokemon

data class ResultViewState(val url : String, val name :String, val imageUrl :String) {
    companion object {
        fun create(url: String, name: String, imageUrl: String): ResultViewState {
            return ResultViewState(url, name, imageUrl)
        }
    }
}