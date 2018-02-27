package `in`.co.kshitijjain.pokemonkotlin.common.di

import `in`.co.kshitijjain.pokemonkotlin.pokemon.PokemonActivity
import `in`.co.kshitijjain.pokemonkotlin.pokemon.di.PokemonActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [(PokemonActivityModule::class)])
    abstract fun bindMainActivity(): PokemonActivity
}
