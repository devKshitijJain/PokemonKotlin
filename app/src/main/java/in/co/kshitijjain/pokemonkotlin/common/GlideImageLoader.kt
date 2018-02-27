package `in`.co.kshitijjain.pokemonkotlin.common

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

internal class GlideImageLoader(private val diskCacheStrategy: DiskCacheStrategy,
                                private val requestManager: RequestManager) : ImageLoader {

    override fun load(uri: String): ImageLoader.RequestBuilder {
        val requestBuilder = requestManager.load(uri)
        return GlideRequestBuilder(diskCacheStrategy, requestBuilder)
    }

    override fun clear(imageView: ImageView) {
        requestManager.clear(imageView)
    }

    private class GlideRequestBuilder internal constructor(diskCacheStrategy: DiskCacheStrategy,
                                                           private val requestBuilder:
                                                           com.bumptech.glide.
                                                           RequestBuilder<Drawable>)
        : ImageLoader.RequestBuilder {
        private val requestOptions: RequestOptions = RequestOptions()
                .diskCacheStrategy(diskCacheStrategy)

        @SuppressLint("CheckResult")
        override fun withPlaceholder(@DrawableRes drawableResourceId: Int):
                ImageLoader.RequestBuilder {
            requestOptions.placeholder(drawableResourceId)
            return this
        }

        @SuppressLint("CheckResult")
        override fun withPlaceholder(drawable: Drawable): ImageLoader.RequestBuilder {
            requestOptions.placeholder(drawable)
            return this
        }

        @SuppressLint("CheckResult")
        override fun withListener(listener: ImageLoader.Listener): ImageLoader.RequestBuilder {
            requestBuilder.listener(RelayingRequestListener(listener))
            return this
        }

        @SuppressLint("CheckResult")
        override fun centerInside(): ImageLoader.RequestBuilder {
            requestOptions.centerInside()
            return this
        }

        @SuppressLint("CheckResult")
        override fun into(imageView: ImageView) {
            requestBuilder.apply(requestOptions)
                    .into(imageView)
        }
    }

    private class RelayingRequestListener internal constructor(private val listener:
                                                               ImageLoader.Listener)
        : RequestListener<Drawable> {

        override fun onLoadFailed(e: GlideException?, o: Any, target: Target<Drawable>, b: Boolean):
                Boolean {
            e?.let { error -> listener.onError(error) }
            return false
        }

        override fun onResourceReady(drawable: Drawable, o: Any, target: Target<Drawable>,
                                     dataSource: DataSource, b: Boolean): Boolean {
            listener.onSuccess()
            return false
        }
    }

    class Factory internal constructor(private val diskCacheStrategy: DiskCacheStrategy)
        : ImageLoader.Factory {

        override fun create(context: Context): ImageLoader {
            return GlideImageLoader(diskCacheStrategy, Glide.with(context))
        }
    }
}