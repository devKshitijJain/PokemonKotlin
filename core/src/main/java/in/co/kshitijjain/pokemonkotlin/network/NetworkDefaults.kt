package `in`.co.kshitijjain.pokemonkotlin.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

class NetworkDefaults private constructor() {

    init {
        throw UnsupportedOperationException("No instance allowed!")
    }

    companion object {

        private const val DEFAULT_TIMEOUT = 30
        private val OK_HTTP_CLIENT = OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .build()
        private val CALL_ADAPTER_FACTORY = RxJava2CallAdapterFactory.create()

        private fun okHttpClient(): OkHttpClient {
            return OK_HTTP_CLIENT
        }

        fun retrofit(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl("http://invalid.value")
                    .client(okHttpClient())
                    .addCallAdapterFactory(CALL_ADAPTER_FACTORY)
                    .build()
        }
    }
}