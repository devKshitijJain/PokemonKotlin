package `in`.co.kshitijjain.pokemonkotlin.pokemon

import `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate.PokemonViewState
import `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate.ResultViewState
import java.util.ArrayList

class PokemonViewStateFixtures {

    private val resultViewStates = ArrayList<ResultViewState>()

    companion object {
        fun aPokemonViewState(): PokemonViewStateFixtures {
            return PokemonViewStateFixtures()
        }
    }

    fun withResultViewState(resultViewState: ResultViewState): PokemonViewStateFixtures {
        resultViewStates.add(resultViewState)
        return this
    }

    fun toIdle(): PokemonViewState.Idle {
        return PokemonViewState.create(resultViewStates)
    }

    fun toLoading(): PokemonViewState.Loading {
        return toIdle().toLoading()
    }

    fun toError(type: PokemonViewState.Error.Type, cause: Throwable): PokemonViewState.Error {
        return toIdle().toError(type, cause)
    }
}