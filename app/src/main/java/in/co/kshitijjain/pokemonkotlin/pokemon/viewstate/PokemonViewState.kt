package `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate

import com.google.auto.value.AutoValue

abstract class PokemonViewState {

    abstract fun results(): List<ResultViewState>

    abstract fun accept(visitor: Visitor)

    open fun toLoading(): Loading {
        return Builder(this)
                .buildLoading()
    }

    fun toError(type: Error.Type, cause: Throwable): Error {
        return Builder(this)
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

        internal fun buildIdle(): Idle {
            return AutoValue_PokemonViewState_Idle(results)
        }

        internal fun buildLoading(): Loading {
            return AutoValue_PokemonViewState_Loading(results)
        }

        internal fun buildError(type: Error.Type, cause: Throwable)
                : Error {
            return AutoValue_PokemonViewState_Error(results, cause, type)
        }
    }

    interface Visitor {

        fun visit(idle: Idle)

        fun visit(loading: Loading)

        fun visit(error: Error)
    }

    companion object {

        fun create(results: List<ResultViewState>): Idle {
            return Builder(results)
                    .buildIdle()
        }

        fun empty(): PokemonViewState {
            return create(emptyList())
        }
    }
}