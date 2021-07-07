package ru.androidschool.intensiv.data.movies.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.androidschool.intensiv.data.movies.entity.MovieType

class MovieTypeDbConverter {

    @TypeConverter
    fun fromMovieType(value: MovieType): String =
        Gson().toJson(value, object : TypeToken<MovieType>() {}.type)

    @TypeConverter
    fun toMovieType(value: String): MovieType =
        Gson().fromJson(value, object : TypeToken<MovieType>() {}.type)
}
