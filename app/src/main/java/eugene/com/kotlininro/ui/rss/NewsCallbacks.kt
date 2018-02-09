package eugene.com.kotlininro.ui.rss


class NewsCallbacks {
    interface AdapterCallbacks {
        fun onNewsItemClicked(url: String)
    }
}
