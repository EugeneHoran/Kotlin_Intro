package eugene.com.kotlininro.ui.rss


import android.support.annotation.ColorInt
import android.support.v7.widget.Toolbar

class NewsCallbacks {
    interface AdapterCallbacks {
        fun onNewsItemClicked(url: String)
    }

    interface FragmentCallbacks {
        fun initActionbar(toolbar: Toolbar)
        fun navIconColor(@ColorInt color: Int)
    }
}
