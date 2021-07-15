package ru.androidschool.intensiv.data.tvshows.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import ru.androidschool.intensiv.data.tvshows.mappers.TvShowsResultMapper
import ru.androidschool.intensiv.data.tvshows.vo.TvShow
import ru.androidschool.intensiv.network.MovieApiClient
import java.io.IOException

class TvShowsPagingSource : PagingSource<Int, TvShow>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShow> {
        val position = params.key ?: SHOWS_STARTING_PAGE_INDEX
        return try {
            val response =
                TvShowsResultMapper.toValueObject(MovieApiClient.apiClient.getTvPopular(page = position))
            val tvShows = response.results

            LoadResult.Page(
                data = tvShows,
                prevKey = if (position == SHOWS_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (tvShows.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TvShow>): Int? = null

    companion object {
        private const val SHOWS_STARTING_PAGE_INDEX = 1
    }
}
