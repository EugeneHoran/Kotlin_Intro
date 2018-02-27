package eugene.com.kotlininro.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import eugene.com.kotlininro.db.dao.NewsDao
import eugene.com.kotlininro.db.entities.NewsStation

@Database(entities = [NewsStation::class], version = 1, exportSchema = false)
@TypeConverters(NewsTypeConverters::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
}