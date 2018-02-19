package `in`.co.kshitijjain.pokemonkotlin.common

import android.app.Application
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
import dagger.BindsInstance


@Singleton
@Component(modules = [
    (AppModule::class),
    (AndroidInjectionModule::class),
    (AndroidSupportInjectionModule::class),
    (ActivityBindingModule::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

    fun inject(application: PokemonApplication)
}