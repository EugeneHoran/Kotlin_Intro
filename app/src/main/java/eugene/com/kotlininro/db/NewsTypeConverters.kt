package eugene.com.kotlininro.db

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import eugene.com.kotlininro.db.entities.NewsStationView

class NewsTypeConverters {
    @TypeConverter
    fun newsStationViewToString(reviewString: String?): NewsStationView? {
        if (reviewString == null) {
            return null
        }
        val type = object : TypeToken<NewsStationView>() {

        }.type
        return Gson().fromJson<NewsStationView>(reviewString, type)
    }

    @TypeConverter
    fun newsStationViewToJson(details: NewsStationView): String {
        return Gson().toJson(details)
    }
}
