package ru.androidschool.intensiv.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import ru.androidschool.intensiv.data.actors.MovieActorsRepository
import ru.androidschool.intensiv.data.details.DetailsMovieRepository
import ru.androidschool.intensiv.data.movies.LikedMoviesRepository
import ru.androidschool.intensiv.data.movies.entity.MovieDbEntity
import ru.androidschool.intensiv.data.movies.entity.MovieType
import ru.androidschool.intensiv.domain.entity.MovieDetails
import ru.androidschool.intensiv.network.entity.Actor
import ru.androidschool.intensiv.network.entity.CreditsResponse
import ru.androidschool.intensiv.network.entity.MovieDetailResponse
import timber.log.Timber

class MovieDetailsViewModel(movieId: Long?) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _movieDetails: MutableLiveData<MovieDetailResponse> = MutableLiveData()
    val movieDetails: LiveData<MovieDetailResponse> = _movieDetails

    private val _actors: MutableLiveData<List<Actor>> = MutableLiveData()
    val actors: LiveData<List<Actor>> = _actors

    private val _isLiked: MutableLiveData<Boolean> = MutableLiveData()
    val isLiked: LiveData<Boolean> = _isLiked

    private val _type: MutableLiveData<MovieType> = MutableLiveData()

    private val _isDownloading: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>().apply { value = false }
    val isDownloading: LiveData<Boolean> = _isDownloading

    init {
        movieId?.let {
            getMovieData(it)
        }
    }

    fun onLikeClicked(isLiked: Boolean) {
        _movieDetails.value?.let {
            LikedMoviesRepository.update(
                MovieDbEntity(
                    movieId = it.id,
                    posterPath = it.posterPath,
                    overview = it.overview ?: "",
                    releaseDate = it.releaseDate,
                    originalTitle = it.originalTitle,
                    originalLanguage = it.originalLanguage,
                    title = it.title,
                    backdropPath = it.backdropPath,
                    popularity = it.popularity,
                    voteAverage = it.voteAverage,
                    isLiked = isLiked,
                    movieType = _type.value ?: MovieType.POPULAR
                )
            )?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    _isLiked.value = isLiked
                    Timber.d("isLiked = $isLiked ")
                },
                    { e -> Timber.e(e.localizedMessage) })
        }
    }

    private fun getMovieData(id: Long) {
        compositeDisposable.add(
            Single.zip(
                DetailsMovieRepository.getDetails(id),
                MovieActorsRepository.getActors(id),
                BiFunction<MovieDetailResponse, CreditsResponse, MovieDetails> { details, actors ->
                    MovieDetails(
                        movieInfo = details,
                        actors = actors
                    )
                }
            )
                .doOnSubscribe { _isDownloading.value = true }
                .doFinally { _isDownloading.value = false }
                .subscribe({ movieDetails ->
                    _movieDetails.value = movieDetails.movieInfo
                    _actors.value = movieDetails.actors.cast
                    isLiked(movieDetails.movieInfo.id)
                },
                    { e -> Timber.d(e.localizedMessage) })
        )
    }

    private fun isLiked(id: Long) {
        val disposable = LikedMoviesRepository.getLiked(id = id)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ movie ->
                _isLiked.value = movie.isLiked
                _type.value = movie.movieType
            }, {
                _isLiked.value = false
            })
        disposable?.let { compositeDisposable.add(it) }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
