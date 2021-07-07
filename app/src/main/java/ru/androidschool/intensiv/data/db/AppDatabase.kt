package ru.androidschool.intensiv.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.androidschool.intensiv.data.movies.db.MovieTypeDbConverter
import ru.androidschool.intensiv.data.movies.db.MoviesDao
import ru.androidschool.intensiv.data.movies.entity.MovieDbEntity
import ru.androidschool.intensiv.utils.DB_NAME

@Database(entities = [MovieDbEntity::class], version = 1)
@TypeConverters(
    value = [MovieTypeDbConverter::class]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MoviesDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun get(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, DB_NAME
                )
                    .build()
            }
            return instance!!
        }
    }
}
