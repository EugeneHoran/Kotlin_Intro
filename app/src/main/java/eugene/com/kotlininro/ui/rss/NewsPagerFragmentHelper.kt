package eugene.com.kotlininro.ui.rss

import android.animation.ArgbEvaluator
import eugene.com.kotlininro.db.entities.NewsStation
import eugene.com.kotlininro.db.entities.NewsStationView
import java.util.*

class NewsPagerFragmentHelper {
    private val argbEvaluator = ArgbEvaluator()
    private val newsStationList = ArrayList<NewsStation>()

    fun setList(newsStationList: List<NewsStation>) {
        this.newsStationList.clear()
        this.newsStationList.addAll(newsStationList)
    }

    fun getColorsFormatted(adapterCount: Int, position: Int, positionOffset: Float): NewsStationView? {
        if (newsStationList.isEmpty()) {
            return null
        }
        val colorsNews: NewsStationView
        if (position < adapterCount - 1 && position < newsStationList.size - 1) {
            val newsStationView = newsStationList[position].newsStationView
            val (_, colorPrimary, colorPrimaryDark, colorAccent) = newsStationList[position + 1].newsStationView
            colorsNews = NewsStationView(
                    argbEvaluator.evaluate(positionOffset, newsStationView.colorPrimary, colorPrimary) as Int,
                    argbEvaluator.evaluate(positionOffset, newsStationView.colorPrimaryDark, colorPrimaryDark) as Int,
                    argbEvaluator.evaluate(positionOffset, newsStationView.colorAccent, colorAccent) as Int
            )
        } else {
            val newsStationView = newsStationList[newsStationList.size - 1].newsStationView
            colorsNews = NewsStationView(
                    newsStationView.colorPrimary,
                    newsStationView.colorPrimaryDark,
                    newsStationView.colorAccent
            )
        }
        return colorsNews
    }
}
