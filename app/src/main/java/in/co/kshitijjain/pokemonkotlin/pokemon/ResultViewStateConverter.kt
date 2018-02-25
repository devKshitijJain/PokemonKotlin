package `in`.co.kshitijjain.pokemonkotlin.pokemon

import retrofit2.Converter

class ResultViewStateConverter : Converter<Result, ResultViewState> {
    override fun convert(result: Result): ResultViewState {
        return ResultViewState.create(result.url,
                getCapitalizedNameFrom(result.name),
                getImageNumberFrom(result.url))
    }

    private fun getCapitalizedNameFrom(name: String): String {
        return name.substring(0, 1).toUpperCase() + name.substring(1)
    }

    private fun getImageNumberFrom(url: String): String {
        val urlParts = url.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val number = Integer.parseInt(urlParts[urlParts.size - 1])
        return "http://pokeapi.co/media/sprites/pokemon/$number.png"
    }
}