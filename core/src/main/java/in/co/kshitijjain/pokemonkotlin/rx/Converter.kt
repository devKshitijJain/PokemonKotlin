package `in`.co.kshitijjain.pokemonkotlin.rx

import io.reactivex.functions.Function

interface Converter<T, R> : Function<T, R>