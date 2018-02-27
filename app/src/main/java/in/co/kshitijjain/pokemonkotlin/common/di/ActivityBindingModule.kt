package `in`.co.kshitijjain.pokemonkotlin.common.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import `in`.co.kshitijjain.pokemonkotlin.pokemon.PokemonActivity
import `in`.co.kshitijjain.pokemonkotlin.pokemon.di.PokemonActivityModule


@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [(PokemonActivityModule::class)])
    abstract fun bindMainActivity(): PokemonActivity
}
