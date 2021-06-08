package ru.androidschool.intensiv.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import ru.androidschool.intensiv.R

fun ImageView.loadTransformationImage(imgUrl: String?, transformation: Transformation) {
    Picasso.get()
        .load(imgUrl)
        .transform(transformation)
        .into(this)
}

fun ImageView.loadImage(imgUrl: String?) {
    Picasso.get()
        .load(imgUrl)
        .into(this)
}

fun ImageView.loadAvatar(@DrawableRes imgRes: Int, transformation: Transformation) {
    Picasso.get()
        .load(imgRes)
        .transform(transformation)
        .placeholder(R.drawable.ic_avatar)
        .into(this)
}
