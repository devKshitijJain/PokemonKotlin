package `in`.co.kshitijjain.pokemonkotlin.pokemon

import com.google.auto.value.AutoValue

abstract class PokemonViewState {

    abstract fun results(): List<ResultViewState>

    abstract fun accept(visitor: PokemonViewState.Visitor)

    open fun toLoading(): PokemonViewState.Loading {
        return PokemonViewState.Builder(this)
                .buildLoading()
    }

    fun toError(type: PokemonViewState.Error.Type, cause: Throwable): PokemonViewState.Error {
        return PokemonViewState.Builder(this)
                .buildError(type, cause)
    }

    @AutoValue
    abstract class Loading : PokemonViewState() {

        override fun accept(visitor: Visitor) {
            visitor.visit(this)
        }
    }

    @AutoValue
    abstract class Idle : PokemonViewState() {

        override fun toLoading(): Loading {
            return Builder(this)
                    .buildLoading()
        }

        override fun accept(visitor: Visitor) {
            visitor.visit(this)
        }
    }

    @AutoValue
    abstract class Error : PokemonViewState() {

        abstract fun cause(): Throwable

        override fun accept(visitor: Visitor) {
            visitor.visit(this)
        }

        abstract fun type(): Type

        enum class Type {
            SERVER,
            CONNECTION,
            UNKNOWN
        }
    }

    private class Builder internal constructor(private val results: List<ResultViewState>) {

        internal constructor(pokemonViewState: PokemonViewState)
                : this(pokemonViewState.results())

        internal fun buildIdle(): PokemonViewState.Idle {
            return AutoValue_PokemonViewState_Idle(results)
        }

        internal fun buildLoading(): PokemonViewState.Loading {
            return AutoValue_PokemonViewState_Loading(results)
        }

        internal fun buildError(type: PokemonViewState.Error.Type, cause: Throwable)
                : PokemonViewState.Error {
            return AutoValue_PokemonViewState_Error(results, cause, type)
        }
    }

    interface Visitor {

        fun visit(idle: PokemonViewState.Idle)

        fun visit(loading: PokemonViewState.Loading)

        fun visit(error: PokemonViewState.Error)
    }

    companion object {

        fun create(results: List<ResultViewState>): PokemonViewState.Idle {
            return PokemonViewState.Builder(results)
                    .buildIdle()
        }

        fun empty(): PokemonViewState {
            return create(emptyList())
        }
    }
}