package eugene.com.kotlininro.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import eugene.com.kotlininro.db.entities.NewsStation

@Dao
abstract class NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertNewsList(newsStationList: List<NewsStation>)

    @Query("SELECT * FROM news_stations")
    abstract fun getNews(): LiveData<List<NewsStation>>
}
