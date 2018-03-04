package `in`.co.kshitijjain.pokemonkotlin.json

import com.squareup.moshi.Moshi

class JsonDefaults private constructor() {

    init {
        throw UnsupportedOperationException("No instance allowed!")
    }

    companion object {

        private val MOSHI = Moshi.Builder().build()

        fun moshi(): Moshi {
            return MOSHI
        }
    }
}