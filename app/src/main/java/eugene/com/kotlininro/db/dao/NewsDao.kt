package eugene.com.kotlininro.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import eugene.com.kotlininro.db.entities.NewsStation

@Dao
abstract class NewsDao {
    // Insert, Update, Delete
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertData(newsStationList: List<NewsStation>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(newsStation: NewsStation)

    @Delete
    abstract fun deleteStation(station: NewsStation)

    // Query
    @Query("SELECT * FROM news_stations")
    abstract fun getNewsStations(): LiveData<List<NewsStation>>

    @Query("SELECT * FROM news_stations WHERE show = 1")
    abstract fun getSelectedNewsStations(): LiveData<List<NewsStation>>
}