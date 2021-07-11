package ru.androidschool.intensiv.di

import org.koin.dsl.module
import ru.androidschool.intensiv.data.actors.repository.MovieActorsRepository
import ru.androidschool.intensiv.data.db.AppDatabase
import ru.androidschool.intensiv.data.details.repository.DetailsMovieRepository
import ru.androidschool.intensiv.data.movies.repository.LikedMoviesRepository
import ru.androidschool.intensiv.domain.repository.ActorsRepository
import ru.androidschool.intensiv.domain.repository.DetailsRepository
import ru.androidschool.intensiv.domain.repository.LikeRepository
import ru.androidschool.intensiv.domain.usecase.ActorsUseCase
import ru.androidschool.intensiv.domain.usecase.DetailsUseCase
import ru.androidschool.intensiv.domain.usecase.LikeUseCase
import ru.androidschool.intensiv.network.MovieApiClient

val dataModule = module {
    single { AppDatabase.get(get()) }
    single { MovieApiClient.apiClient }
}

val domainModule = module {
    single<DetailsRepository> { DetailsMovieRepository(get()) }
    single<ActorsRepository> { MovieActorsRepository(get()) }
    single<LikeRepository> { LikedMoviesRepository(get()) }
    single { DetailsUseCase(get()) }
    single { ActorsUseCase(get()) }
    single { LikeUseCase(get()) }
}
