package eugene.com.kotlininro.dagger.module

import dagger.Module
import dagger.Provides
import eugene.com.kotlininro.NewsApplication
import eugene.com.kotlininro.db.NewsDatabase
import eugene.com.kotlininro.db.dao.NewsDao
import javax.inject.Singleton


@Module
class RoomModule(newsApplication: NewsApplication) {

    private var newsDatabase: NewsDatabase = NewsDatabase.getInstance(newsApplication)

    @Singleton
    @Provides
    fun providesRoomDatabase(): NewsDatabase {
        return newsDatabase
    }

    @Singleton
    @Provides
    fun providesNewsDao(): NewsDao {
        return providesRoomDatabase().getNewsDao()
    }
}