package ru.androidschool.intensiv.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.getSystemService
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.R

fun ImageView.loadTransformationImage(imgUrl: String?, transformation: Transformation) {
    Picasso.get()
        .load(BuildConfig.POSTER_BASE_URL + imgUrl)
        .transform(transformation)
        .into(this)
}

fun ImageView.loadImage(imgUrl: String?) {
    Picasso.get()
        .load(BuildConfig.POSTER_BASE_URL + imgUrl)
        .into(this)
}

fun ImageView.loadAvatar(@DrawableRes imgRes: Int, transformation: Transformation) {
    Picasso.get()
        .load(imgRes)
        .transform(transformation)
        .placeholder(R.drawable.ic_avatar)
        .into(this)
}

fun Activity.hideKeyboard() {
    val imm = getSystemService<InputMethodManager>()
    imm?.hideSoftInputFromWindow(
        window.decorView.findViewById<View>(android.R.id.content).windowToken,
        0
    )
}

fun <T> Single<T>.on(): Single<T> =
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
