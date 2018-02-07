package eugene.com.kotlininro.db

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import eugene.com.kotlininro.db.entities.NewsStationLink
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

    @TypeConverter
    fun newsStationLinksToString(linksString: String?): List<NewsStationLink>? {
        if (linksString == null) {
            return null
        }
        val type = object : TypeToken<List<NewsStationLink>>() {

        }.type
        return Gson().fromJson<List<NewsStationLink>>(linksString, type)
    }

    @TypeConverter
    fun newsStationLinksToJson(newsStationLink: List<NewsStationLink>): String {
        return Gson().toJson(newsStationLink)
    }
}
