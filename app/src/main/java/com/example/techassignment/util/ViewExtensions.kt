package com.example.techassignment.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.techassignment.R
import de.hdodenhof.circleimageview.CircleImageView


fun ImageView.loadImage(
    path: String?,
    @DrawableRes placeHolder: Int = R.drawable.ic_place_holder,
    success: () -> Unit = {},
    error: () -> Unit = {},
    setScale: Boolean = false
) {
    if (setScale && this !is CircleImageView)
        this.scaleType = ImageView.ScaleType.FIT_XY

    if (path.isNullOrEmpty())
        setImageResource(placeHolder)
    else {
        getRequestBuilder(context, path, placeHolder, success, error)
            .error(getRequestBuilder(context, path, placeHolder, success, error))
            .into(this)
    }

}

private fun getRequestBuilder(
    context: Context, path: String?,
    @DrawableRes placeHolder: Int = R.drawable.ic_place_holder,
    success: () -> Unit = {},
    error: () -> Unit = {}
) = Glide.with(context)
    .load(path)
    .placeholder(
        context.getDrawable(
            placeHolder
        )
    ).thumbnail(0.05f)
    .listener(object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: com.bumptech.glide.request.target.Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            error()
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: com.bumptech.glide.request.target.Target<Drawable>?,
            dataSource: com.bumptech.glide.load.DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            success()
            return false
        }
    })


