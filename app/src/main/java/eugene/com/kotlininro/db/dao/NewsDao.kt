package eugene.com.kotlininro.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import eugene.com.kotlininro.db.entities.NewsStation

@Dao
abstract class NewsDao {
    // Insert (single, list), Update, Delete
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertNews(newsStation: NewsStation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertNewsList(newsStationList: List<NewsStation>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateNews(newsStation: NewsStation)

    @Delete
    abstract fun deleteNews(station: NewsStation)

    // Query All, Selected
    @Query("SELECT * FROM news_stations")
    abstract fun getNews(): LiveData<List<NewsStation>>

    @Query("SELECT * FROM news_stations WHERE show = 1")
    abstract fun getSelectedNews(): LiveData<List<NewsStation>>
}