package ir.org.tavanesh.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import ir.org.tavanesh.R
import jp.wasabeef.glide.transformations.CropCircleTransformation

fun ImageView.loadUrl(url: String) {
    Glide.with(context)
        .load(url)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.temp_logo)
                .error(R.drawable.temp_logo)
        )
        .into(this)
}

fun ImageView.loadCircle(url: String) {
    Glide.with(context)
        .load(url)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.temp_logo)
                .error(R.drawable.temp_logo)
        )
        .apply(bitmapTransform(CropCircleTransformation()))
        .into(this)
}