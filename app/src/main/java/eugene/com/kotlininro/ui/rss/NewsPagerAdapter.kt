package eugene.com.kotlininro.ui.rss

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import eugene.com.kotlininro.db.entities.NewsStation

class NewsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private var newsList: List<NewsStation>? = null
    fun setNewsList(newsList: List<NewsStation>) {
        this.newsList = newsList
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        return NewsStationFragment.newInstance(newsList!![position])
    }

    override fun getCount(): Int {
        return if (newsList != null) newsList!!.size else 0
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return newsList!![position].title
    }
}