package eugene.com.kotlininro.ui.common

import android.support.v4.app.FragmentManager
import eugene.com.kotlininro.R
import eugene.com.kotlininro.ui.FragmentMain
import eugene.com.kotlininro.ui.rss.RssPagerFragment

class NavigationController(fragmentManager: FragmentManager) {
    private var fm: FragmentManager = fragmentManager
    private var container: Int = R.id.container

    fun navToPagerFragment() {
        fm.beginTransaction()
                .replace(container, RssPagerFragment.newInstance())
                .commit()
    }

    fun navToFrag(text: String) {
        fm.beginTransaction()
                .replace(container, FragmentMain.newInstance(text))
                .commit()
    }
}