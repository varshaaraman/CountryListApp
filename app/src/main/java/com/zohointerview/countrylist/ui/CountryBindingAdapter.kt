package com.zohointerview.countrylist.ui

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.zohointerview.countrylist.R
import com.zohointerview.countrylist.domain.Country
import com.zohointerview.countrylist.ui.master.adapter.FlagAdapter
import timber.log.Timber

/**
 * Class for defining custom binding functions
 */

/**
 *Attribute submit list data to the adapter
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Country>?) {
    val adapter = recyclerView.adapter as FlagAdapter
    adapter.submitList(data)
}

/**
 * Used for fetching image using glide or GlideToVectorYou
 * For SVG -> use GlideToVectorYou(Extension of glide for vector images)
 * others -> use Glide
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        val extension = imgUri.toString().substring(imgUri.toString().lastIndexOf(".") + 1)
        when (extension.lowercase()) {
            "svg" -> {
                GlideToVectorYou.init().with(imgView.context).requestBuilder
                    .load(imgUri)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.ic_loading)
                            .error(R.drawable.ic_broken)
                    )
                    .into(imgView)
            }
            else -> {
                Glide.with(imgView.context)
                    .load(imgUri)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.ic_loading)
                            .error(R.drawable.ic_broken)
                    )
                    .into(imgView)

            }
        }
        Timber.d("The uri is $imgUri with extension %s", extension)

    }
}


