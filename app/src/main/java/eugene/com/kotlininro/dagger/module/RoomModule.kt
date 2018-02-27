package eugene.com.kotlininro.dagger.module

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import dagger.Module
import dagger.Provides
import eugene.com.kotlininro.NewsApplication
import eugene.com.kotlininro.db.NewsDataGenerator
import eugene.com.kotlininro.db.NewsDatabase
import eugene.com.kotlininro.db.dao.NewsDao
import eugene.com.kotlininro.util.ioThread
import javax.inject.Singleton

@Module
class RoomModule(newsApplication: NewsApplication) {
    private lateinit var database: NewsDatabase

    init {
        database = Room.databaseBuilder(newsApplication, NewsDatabase::class.java, "news.rss.db").addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                ioThread {
                    database.getNewsDao().insertNewsList(NewsDataGenerator().getInitNewsStationList())
                }
            }
        }).build()
    }

    @Singleton
    @Provides
    fun providesRoomDatabase(): NewsDatabase {
        return database
    }

    @Singleton
    @Provides
    fun providesNewsDao(): NewsDao {
        return database.getNewsDao()
    }
}