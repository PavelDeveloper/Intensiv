package ru.androidschool.intensiv

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import ru.androidschool.intensiv.data.db.AppDatabase

@RunWith(AndroidJUnit4::class)
class DatabaseRule : TestWatcher() {

    lateinit var database: AppDatabase

    override fun starting(description: Description?) {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext<Context>(),
            AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    override fun finished(description: Description?) {
        database.close()
    }
}
