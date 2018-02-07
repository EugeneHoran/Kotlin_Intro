package eugene.com.kotlininro.db

import eugene.com.kotlininro.R
import eugene.com.kotlininro.db.entities.NewsStation
import eugene.com.kotlininro.db.entities.NewsStationLink
import eugene.com.kotlininro.db.entities.NewsStationView
import eugene.com.kotlininro.util.ColorUtil
import java.util.*

class NewsDataGenerator {
    private val URL_CNN_LATEST = "http://rss.cnn.com/rss/cnn_latest.rss"
    private val URL_CNN_TOP = "http://rss.cnn.com/rss/cnn_topstories.rss"
    private val URL_CNN_WORLD = "http://rss.cnn.com/rss/cnn_world.rss"
    private val URL_CNN_BUSINESS = "http://rss.cnn.com/rss/money_latest.rss"
    private val URL_CNN_POLITICS = "http://rss.cnn.com/rss/cnn_allpolitics.rss"
    private val URL_FOX_LATEST = "http://feeds.foxnews.com/foxnews/latest?format=xml"
    private val URL_FOX_POPULAR = "http://feeds.foxnews.com/foxnews/most-popular?format=xml"
    private val URL_BBC_PRIMARY = "http://feeds.bbci.co.uk/news/rss.xml"
    private val URL_BBC_SECONDARY = "http://feeds.bbci.co.uk/news/world/rss.xml"
    private val URL_NYT_PRIMARY = "http://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml"
    private val URL_NYT_SECONDARY = "http://rss.nytimes.com/services/xml/rss/nyt/MostViewed.xml"
    private val URL_CNBC_PRIMARY = "https://www.cnbc.com/id/100003114/device/rss/rss.html"
    private val URL_CNBC_SECONDARY = "https://www.cnbc.com/id/100727362/device/rss/rss.html"
    private val URL_BREITBART = "http://feeds.feedburner.com/breitbart"
    private val URL_THE_DAILY_BEAST = "http://rss.cnn.com/rss/cnn_latest.rss"

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
        val links = ArrayList<NewsStationLink>()
        links.add(NewsStationLink(0, "Latest Stories", URL_CNN_LATEST, true))
        links.add(NewsStationLink(1, "Top Stories", URL_CNN_TOP))
        links.add(NewsStationLink(2, "World Stories", URL_CNN_WORLD))
        links.add(NewsStationLink(3, "Business", URL_CNN_BUSINESS))
        links.add(NewsStationLink(4, "Politics", URL_CNN_POLITICS))
        return NewsStation("CNN",
                links,
                NewsStationView(
                        R.drawable.logo_cnn,
                        ColorUtil.getColors("#C70005")
                ))
    }

    private fun fox(): NewsStation {
        val links = ArrayList<NewsStationLink>()
        links.add(NewsStationLink(0, "Latest Stories", URL_FOX_LATEST, true))
        links.add(NewsStationLink(1, "Popular Stories", URL_FOX_POPULAR))
        return NewsStation(
                "Fox News",
                links,
                NewsStationView(
                        R.drawable.logo_fox,
                        ColorUtil.getColors("#003265")
                )
        )
    }

    private fun nyt(): NewsStation {
        val links = ArrayList<NewsStationLink>()
        links.add(NewsStationLink(0, "Home Page Stories", URL_NYT_PRIMARY, true))
        links.add(NewsStationLink(1, "Most Viewed Stories", URL_NYT_SECONDARY))
        return NewsStation(
                "New York Times",
                links,
                NewsStationView(
                        R.drawable.logo_nyt,
                        ColorUtil.getColors("#FFFFFF")
                )
        )
    }

    private fun breitbart(): NewsStation {
        val links = ArrayList<NewsStationLink>()
        links.add(NewsStationLink(0, "Top Stories", URL_BREITBART, true))
        return NewsStation(
                "Breitbart",
                links,
                NewsStationView(
                        R.drawable.logo_breitbart,
                        ColorUtil.getColors("#FF5202")
                )
        )
    }

    private fun bbc(): NewsStation {
        val links = ArrayList<NewsStationLink>()
        links.add(NewsStationLink(0, "Top Stories", URL_BBC_PRIMARY, true))
        links.add(NewsStationLink(1, "World Stories", URL_BBC_SECONDARY))
        return NewsStation(
                "BBC",
                links,
                NewsStationView(
                        R.drawable.logo_bbc,
                        ColorUtil.getColors("#BB1919")
                )
        )
    }

    private fun dailyBeast(): NewsStation {
        val links = ArrayList<NewsStationLink>()
        links.add(NewsStationLink(0, "Latest Stories", URL_THE_DAILY_BEAST, true))
        return NewsStation(
                "The Daily Beast",
                links,
                NewsStationView(
                        R.drawable.logo_daily_beast,
                        ColorUtil.getColors("#303030")
                )
        )
    }

    private fun cnbc(): NewsStation {
        val links = ArrayList<NewsStationLink>()
        links.add(NewsStationLink(0, "Top Stories", URL_CNBC_PRIMARY, true))
        links.add(NewsStationLink(1, "US News", URL_CNBC_SECONDARY))
        return NewsStation(
                "CNBC",
                links,
                NewsStationView(
                        R.drawable.logo_cnbc,
                        ColorUtil.getColors("#146195")
                )
        )
    }
}