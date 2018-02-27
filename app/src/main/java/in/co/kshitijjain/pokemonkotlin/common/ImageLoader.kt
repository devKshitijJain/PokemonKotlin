package `in`.co.kshitijjain.pokemonkotlin.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.widget.ImageView

interface ImageLoader {

    fun load(uri: String): RequestBuilder

    fun clear(imageView: ImageView)

    interface RequestBuilder {

        fun withPlaceholder(@DrawableRes drawableResourceId: Int): RequestBuilder

        fun withPlaceholder(drawable: Drawable): RequestBuilder

        fun withListener(listener: Listener): RequestBuilder

        fun centerInside(): RequestBuilder

        fun into(imageView: ImageView)
    }

    interface Listener {

        fun onError(e: Exception)

        fun onSuccess()
    }

    interface Factory {

        fun create(context: Context): ImageLoader
    }
}