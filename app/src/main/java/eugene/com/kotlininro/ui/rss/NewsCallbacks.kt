package eugene.com.kotlininro.ui.rss


import android.support.v7.widget.Toolbar

interface NewsCallbacks {
    interface AdapterCallbacks {
        fun onNewsItemClicked(url: String)
    }

    fun initActionbar(toolbar: Toolbar)
}
