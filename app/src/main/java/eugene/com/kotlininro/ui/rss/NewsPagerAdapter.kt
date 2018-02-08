package eugene.com.kotlininro.ui.rss

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import eugene.com.kotlininro.db.entities.NewsStation

class NewsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private var newsStations: List<NewsStation>? = mutableListOf()
    private var fragments: Array<NewsStationFragment?>? = null

    fun setNewsList(newsList: List<NewsStation>) {
        this.newsStations = newsList
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        if (fragments != null && fragments!!.size > position && fragments!![position] != null) {
            return fragments!![position]!!
        }
        if (fragments == null) {
            fragments = arrayOfNulls(count)
        }
        fragments!![position] = NewsStationFragment.newInstance(newsStations!![position])
        return fragments!![position]!!
    }

    override fun getCount(): Int = newsStations!!.size
    override fun getPageTitle(position: Int): CharSequence? = newsStations!![position].title
}