package eugene.com.kotlininro.ui.common

import android.support.v4.app.FragmentManager
import eugene.com.kotlininro.R
import eugene.com.kotlininro.ui.FragmentMain

class NavigationController(fragmentManager: FragmentManager) {
    private var fm: FragmentManager = fragmentManager
    private var container: Int = 0

    init {
        container = R.id.container
    }

    fun navToFrag(text: String) {
        fm.beginTransaction()
                .replace(container, FragmentMain.newInstance(text))
                .commit()
    }
}