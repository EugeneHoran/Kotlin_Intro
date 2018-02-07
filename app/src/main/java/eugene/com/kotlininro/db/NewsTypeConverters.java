package eugene.com.kotlininro.db;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import eugene.com.kotlininro.db.entities.NewsStationLink;
import eugene.com.kotlininro.db.entities.NewsStationView;

public class NewsTypeConverters {
    @TypeConverter
    public static NewsStationView newsStationViewToString(String reviewString) {
        if (reviewString == null) {
            return null;
        }
        Type type = new TypeToken<NewsStationView>() {
        }.getType();
        return new Gson().fromJson(reviewString, type);
    }

    @TypeConverter
    public static String newsStationViewToJson(NewsStationView details) {
        return new Gson().toJson(details);
    }

    @TypeConverter
    public static List<NewsStationLink> newsStationLinksToString(String linksString) {
        if (linksString == null) {
            return null;
        }
        Type type = new TypeToken<List<NewsStationLink>>() {
        }.getType();
        return new Gson().fromJson(linksString, type);
    }

    @TypeConverter
    public static String newsStationLinksToJson(List<NewsStationLink> newsStationLink) {
        return new Gson().toJson(newsStationLink);
    }
}
