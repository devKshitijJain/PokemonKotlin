package `in`.co.kshitijjain.pokemonkotlin.common.di

import `in`.co.kshitijjain.pokemonkotlin.common.GlideImageLoader
import `in`.co.kshitijjain.pokemonkotlin.common.ImageLoader
import android.app.Application
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageLoaderModule {

    @Provides
    @Singleton
    internal fun imageLoader(application: Application): ImageLoader {
        return GlideImageLoader.Factory(DiskCacheStrategy.ALL).create(application)
    }
}