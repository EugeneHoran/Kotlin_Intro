package eugene.com.kotlininro.ui.common

import android.support.v4.app.FragmentManager
import eugene.com.kotlininro.R
import eugene.com.kotlininro.ui.rss.NewsPagerFragment

class NavigationController(fragmentManager: FragmentManager) {
    companion object {
        const val TAG_NEWS_PAGER_FRAGMENT = "tag_news_pager_fragment"
    }

    private var fm: FragmentManager = fragmentManager
    private var container: Int = R.id.container

    fun navToPagerFragment() {
        fm.beginTransaction()
                .replace(container, NewsPagerFragment.newInstance(), TAG_NEWS_PAGER_FRAGMENT)
                .commit()
    }
}