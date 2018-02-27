package `in`.co.kshitijjain.pokemonkotlin.common.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun context(application: Application): Context {
        return application
    }
}