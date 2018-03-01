package eugene.com.kotlininro.dagger.module

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import dagger.Module
import dagger.Provides
import eugene.com.kotlininro.dagger.NewsApplicationScope
import eugene.com.kotlininro.db.NewsDataGenerator
import eugene.com.kotlininro.db.NewsDatabase
import eugene.com.kotlininro.db.dao.NewsDao
import eugene.com.kotlininro.util.ioThread

@Module(includes = [ContextModule::class])
class RoomModule {
    private lateinit var database: NewsDatabase

    @Provides
    @NewsApplicationScope
    fun newsDatabase(context: Context): NewsDatabase {
        database = Room.databaseBuilder(context, NewsDatabase::class.java, "news.rss.db").addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                ioThread {
                    database.getNewsDao().insertNewsList(NewsDataGenerator().getInitNewsStationList())
                }
            }
        }).build()
        return database
    }

    @Provides
    @NewsApplicationScope
    fun newsDao(newsDatabase: NewsDatabase): NewsDao {
        return newsDatabase.getNewsDao()
    }
}