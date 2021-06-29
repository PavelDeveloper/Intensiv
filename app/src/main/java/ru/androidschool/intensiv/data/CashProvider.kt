package ru.androidschool.intensiv.data

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class CashProvider<T>() {

    fun getFlowable(type: RepositoryAccess): Flowable<T> {
        return createFlowable(type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun createFlowable(type: RepositoryAccess): Flowable<T> {
        when (type) {
            RepositoryAccess.OFFLINE -> return createOfflineFlowable()
            RepositoryAccess.REMOTE -> return createRemoteFlowable()
            RepositoryAccess.OFFLINE_FIRST -> {
                val remoteFlowable = createRemoteFlowable()
                return createOfflineFlowable()
                    .onErrorResumeNext(remoteFlowable)
                    .concatWith(remoteFlowable)
            }
            else -> {
                val remoteFlowable = createRemoteFlowable()
                return createOfflineFlowable()
                    .onErrorResumeNext(remoteFlowable)
                    .concatWith(remoteFlowable)
            }
        }
    }

    protected abstract fun createRemoteFlowable(): Flowable<T>

    protected abstract fun createOfflineFlowable(): Flowable<T>
}
