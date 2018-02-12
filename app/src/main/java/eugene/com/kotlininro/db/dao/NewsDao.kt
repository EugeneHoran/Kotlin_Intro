package eugene.com.kotlininro.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import eugene.com.kotlininro.db.entities.NewsStation

@Dao
abstract class NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertNewsList(newsStationList: List<NewsStation>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(newsStation: NewsStation)

    @Query("SELECT * FROM news_stations")
    abstract fun getNews(): LiveData<List<NewsStation>>

    @Query("SELECT * FROM news_stations WHERE show = 1")
    abstract fun getSelectedNews(): LiveData<List<NewsStation>>
}
