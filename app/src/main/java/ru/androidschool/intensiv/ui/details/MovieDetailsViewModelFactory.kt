package ru.androidschool.intensiv.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class MovieDetailsViewModelFactory(private val movieId: Long?) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MovieDetailsViewModel(movieId) as T
}
