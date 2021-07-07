package ru.androidschool.intensiv.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import ru.androidschool.intensiv.data.actors.repository.MovieActorsRepository
import ru.androidschool.intensiv.data.actors.vo.Actor
import ru.androidschool.intensiv.data.actors.vo.Credits
import ru.androidschool.intensiv.data.details.repository.DetailsMovieRepository
import ru.androidschool.intensiv.data.details.vo.MovieDetailInfo
import ru.androidschool.intensiv.data.movies.entity.MovieDbEntity
import ru.androidschool.intensiv.data.movies.entity.MovieType
import ru.androidschool.intensiv.data.movies.repository.LikedMoviesRepository
import ru.androidschool.intensiv.data.movies.vo.MovieDetails
import ru.androidschool.intensiv.domain.usecase.ActorsUseCase
import ru.androidschool.intensiv.domain.usecase.DetailsUseCase
import ru.androidschool.intensiv.domain.usecase.LikeUseCase
import timber.log.Timber

class MovieDetailsViewModel(movieId: Long?) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _movieDetailInfo: MutableLiveData<MovieDetailInfo> = MutableLiveData()
    val movieDetailInfo: LiveData<MovieDetailInfo> = _movieDetailInfo

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
        _movieDetailInfo.value?.let {
            LikeUseCase(LikedMoviesRepository).update(
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
            ).subscribe({
                _isLiked.value = isLiked
                Timber.d("isLiked = $isLiked ")
            },
                { e -> Timber.e(e.localizedMessage) })
        }
    }

    private fun getMovieData(id: Long) {
        compositeDisposable.add(
            Single.zip(
                DetailsUseCase(DetailsMovieRepository).getDetails(id),
                ActorsUseCase(MovieActorsRepository).getActors(id),
                BiFunction<MovieDetailInfo, Credits, MovieDetails> { details, actors ->
                    MovieDetails(
                        movieDetailInfo = details,
                        actors = actors
                    )
                }
            )
                .doOnSubscribe { _isDownloading.value = true }
                .doFinally { _isDownloading.value = false }
                .subscribe({ movieDetails ->
                    _movieDetailInfo.value = movieDetails.movieDetailInfo
                    _actors.value = movieDetails.actors.cast
                    isLiked(movieDetails.movieDetailInfo.id)
                },
                    { e -> Timber.d(e.localizedMessage) })
        )
    }

    private fun isLiked(id: Long) {
        val disposable = LikeUseCase(LikedMoviesRepository).getLiked(id = id)
            .subscribe({ movie ->
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
