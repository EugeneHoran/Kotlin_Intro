package eugene.com.kotlininro.ui.common

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import eugene.com.kotlininro.R
import eugene.com.kotlininro.ui.rss.NewsPagerFragment
import eugene.com.kotlininro.ui.selector.SelectorFragment

class NavigationController(fragmentManager: FragmentManager, callback: Callbacks.NavigationControllerCallbacks) {
    private var fm: FragmentManager = fragmentManager
    private var listener: Callbacks.NavigationControllerCallbacks = callback
    private var container: Int = R.id.container

    companion object {
        const val TAG_NEWS_PAGER_FRAGMENT = "tag_news_pager_fragment"
        const val TAG_NEWS_SELECTOR_FRAGMENT = "tag_news_selector_fragment"
    }

    fun navToSelectorFragment(showNavigationIcon: Boolean) {
        listener.fragmentPopBackEnabled(showNavigationIcon)
        val transaction: FragmentTransaction = fm.beginTransaction()
        val selectorFragment: SelectorFragment = SelectorFragment.newInstance(showNavigationIcon)
        transaction.replace(container, selectorFragment, TAG_NEWS_SELECTOR_FRAGMENT)
        transaction.commit()
    }

    fun navToNewsFragment() {
        listener.fragmentPopBackEnabled(false)
        val transaction: FragmentTransaction = fm.beginTransaction()
        val newsPagerFragment: NewsPagerFragment = NewsPagerFragment.newInstance()
        transaction.replace(container, newsPagerFragment, TAG_NEWS_PAGER_FRAGMENT)
        transaction.commit()
    }
}