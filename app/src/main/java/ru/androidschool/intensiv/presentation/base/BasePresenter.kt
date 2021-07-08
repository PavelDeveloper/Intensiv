package ru.androidschool.intensiv.presentation.base

abstract class BasePresenter<V> {

    protected var view: V? = null

    fun attachView(view: V) {
        this.view = view
    }

    open fun detachView() {
        this.view = null
    }
}
