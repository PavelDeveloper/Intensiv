package ru.androidschool.intensiv.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SearchMovieViewModelFactory(private val query: CharSequence?) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = SearchViewModel(query) as T
}
