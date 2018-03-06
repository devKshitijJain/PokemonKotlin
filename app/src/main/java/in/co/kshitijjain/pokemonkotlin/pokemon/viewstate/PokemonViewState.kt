package `in`.co.kshitijjain.pokemonkotlin.pokemon.viewstate

abstract class PokemonViewState {

    abstract fun results(): List<ResultViewState>

    abstract fun accept(visitor: Visitor)

    abstract class Loading : PokemonViewState() {
        override fun accept(visitor: Visitor) {
            visitor.visit(this)
        }
    }

    abstract class Idle : PokemonViewState() {
        override fun accept(visitor: Visitor) {
            visitor.visit(this)
        }
    }

    abstract class Error : PokemonViewState() {

        abstract fun cause(): Throwable

        abstract fun type(): Type

        enum class Type {
            SERVER,
            CONNECTION,
            UNKNOWN
        }

        override fun accept(visitor: Visitor) {
            visitor.visit(this)
        }
    }

    interface Visitor {

        fun visit(loading: Loading)

        fun visit(idle: Idle)

        fun visit(error: Error)
    }

    private class Builder internal constructor(private val results: List<ResultViewState>) {

        constructor(pokemonViewState: PokemonViewState)
                : this(pokemonViewState.results())

        internal fun buildIdle(): Idle {
            return IdlePokemonViewState(results)
        }

        internal fun buildLoading(): Loading {
            return LoadingPokemonViewState(results)
        }

        internal fun buildError(type: Error.Type, cause: Throwable): Error {
            return ErrorPokemonViewState(results, cause, type)
        }
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

    fun toLoading(): Loading {
        return PokemonViewState.Builder(this)
                .buildLoading()
    }

    fun toError(type: Error.Type, cause: Throwable): Error {
        return PokemonViewState.Builder(this)
                .buildError(type, cause)
    }

    data class LoadingPokemonViewState(private val results: List<ResultViewState>) : Loading() {
        override fun results(): List<ResultViewState> {
            return results
        }
    }

    data class IdlePokemonViewState(private val results: List<ResultViewState>) : Idle() {
        override fun results(): List<ResultViewState> {
            return results
        }
    }

    data class ErrorPokemonViewState(private val results: List<ResultViewState>,
                                     private val cause: Throwable,
                                     private val type: Type) : Error() {
        override fun results(): List<ResultViewState> {
            return results
        }

        override fun cause(): Throwable {
            return cause
        }

        override fun type(): Type {
            return type
        }
    }
}