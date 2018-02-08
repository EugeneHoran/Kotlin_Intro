package eugene.com.kotlininro.db

import eugene.com.kotlininro.R
import eugene.com.kotlininro.db.entities.NewsStation
import eugene.com.kotlininro.db.entities.NewsStationView
import eugene.com.kotlininro.util.ColorUtil
import java.util.*

class NewsDataGenerator {
    companion object {
        private const val URL_CNN = "http://rss.cnn.com/rss/cnn_latest.rss"
        private const val URL_FOX = "http://feeds.foxnews.com/foxnews/latest?format=xml"
        private const val URL_NYT = "http://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml"
        private const val URL_BBC = "http://feeds.bbci.co.uk/news/rss.xml"
        private const val URL_CNBC = "https://www.cnbc.com/id/100003114/device/rss/rss.html"
        private const val URL_BREITBART = "http://feeds.feedburner.com/breitbart"
        private const val URL_THE_DAILY_BEAST = "http://rss.cnn.com/rss/cnn_latest.rss"
    }

    fun getInitNewsStationList(): List<NewsStation> {
        val newsList = ArrayList<NewsStation>()
        // CNN
        newsList.add(cnn())
        // FOX
        newsList.add(fox())
        // NYT
        newsList.add(nyt())
        // BREITBART
        newsList.add(breitbart())
        // BBC
        newsList.add(bbc())
        // THE DAILY BEAST
        newsList.add(dailyBeast())
        // CNBC
        newsList.add(cnbc())
        return newsList
    }

    private fun cnn(): NewsStation {
        return NewsStation("CNN",
                URL_CNN,
                NewsStationView(
                        R.drawable.logo_cnn,
                        ColorUtil.getColors("#C70005")
                ))
    }

    private fun fox(): NewsStation {
        return NewsStation(
                "Fox News",
                URL_FOX,
                NewsStationView(
                        R.drawable.logo_fox,
                        ColorUtil.getColors("#003265")
                )
        )
    }

    private fun nyt(): NewsStation {
        return NewsStation(
                "New York Times",
                URL_NYT,
                NewsStationView(
                        R.drawable.logo_nyt,
                        ColorUtil.getColors("#FFFFFF")
                )
        )
    }

    private fun breitbart(): NewsStation {
        return NewsStation(
                "Breitbart",
                URL_BREITBART,
                NewsStationView(
                        R.drawable.logo_breitbart,
                        ColorUtil.getColors("#FF5202")
                )
        )
    }

    private fun bbc(): NewsStation {
        return NewsStation(
                "BBC",
                URL_BBC,
                NewsStationView(
                        R.drawable.logo_bbc,
                        ColorUtil.getColors("#BB1919")
                )
        )
    }

    private fun dailyBeast(): NewsStation {
        return NewsStation(
                "The Daily Beast",
                URL_THE_DAILY_BEAST,
                NewsStationView(
                        R.drawable.logo_daily_beast,
                        ColorUtil.getColors("#303030")
                )
        )
    }

    private fun cnbc(): NewsStation {
        return NewsStation(
                "CNBC",
                URL_CNBC,
                NewsStationView(
                        R.drawable.logo_cnbc,
                        ColorUtil.getColors("#146195")
                )
        )
    }
}