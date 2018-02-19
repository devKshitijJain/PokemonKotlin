package `in`.co.kshitijjain.pokemonkotlin.common

import dagger.Module
import dagger.android.ContributesAndroidInjector
import `in`.co.kshitijjain.pokemonkotlin.pokemon.PokemonActivity
import `in`.co.kshitijjain.pokemonkotlin.pokemon.PokemonActivityModule


@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [(PokemonActivityModule::class)])
    abstract fun bindMainActivity(): PokemonActivity

}