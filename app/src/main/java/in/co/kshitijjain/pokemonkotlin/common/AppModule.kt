package `in`.co.kshitijjain.pokemonkotlin.common

import android.app.Application
import android.content.Context
import dagger.Module
import javax.inject.Singleton
import dagger.Provides

@Module
class AppModule {

    @Provides
    @Singleton
    fun Context(application: Application): Context {
        return application
    }
}