package `in`.co.kshitijjain.pokemonkotlin.common.di

import android.app.Application
import android.content.Context
import dagger.Module
import javax.inject.Singleton
import dagger.Provides

@Module
class AppModule {

    @Provides
    @Singleton
    fun context(application: Application): Context {
        return application
    }
}