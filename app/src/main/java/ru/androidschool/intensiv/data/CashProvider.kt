package ru.androidschool.intensiv.data

import io.reactivex.Observable

abstract class CashProvider<T>() {

    fun getObservable(type: RepositoryAccess): Observable<T> {
        return createObservable(type)
    }

    private fun createObservable(type: RepositoryAccess): Observable<T> {
        when (type) {
            RepositoryAccess.OFFLINE -> return createOfflineObservable()
            RepositoryAccess.REMOTE -> return createRemoteObservable()
            RepositoryAccess.OFFLINE_FIRST -> {
                val remoteObservable = createRemoteObservable()
                return createOfflineObservable()
                    .onErrorResumeNext(remoteObservable)
                    .concatWith(remoteObservable)
            }
            else -> {
                val remoteObservable = createRemoteObservable()
                return createOfflineObservable()
                    .onErrorResumeNext(remoteObservable)
                    .concatWith(remoteObservable)
            }
        }
    }

    protected abstract fun createRemoteObservable(): Observable<T>

    protected abstract fun createOfflineObservable(): Observable<T>
}
