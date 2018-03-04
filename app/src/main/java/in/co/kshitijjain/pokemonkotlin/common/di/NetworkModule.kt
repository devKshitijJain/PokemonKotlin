package `in`.co.kshitijjain.pokemonkotlin.common.di

import `in`.co.kshitijjain.pokemonkotlin.json.JsonDefaults
import `in`.co.kshitijjain.pokemonkotlin.network.NetworkDefaults
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return NetworkDefaults.retrofit()
                .newBuilder()
                .baseUrl("https://pokeapi.co/")
                .build()
    }

    @Provides
    @Singleton
    fun moshi(): Moshi {
        return JsonDefaults.moshi()
    }
}