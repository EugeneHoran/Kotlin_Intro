package eugene.com.kotlininro.ui.common


class Callbacks {
    interface AdapterCallbacks {
        fun onNewsItemClicked(url: String)
    }

    interface NavigationControllerCallbacks {
        fun fragmentPopBackEnabled(popBackEnabled: Boolean)
    }

    interface FragmentNavigationCallbacks {
        fun navToSelectorFragment(popBackEnabled: Boolean)
        fun navToNewsFragment()
    }
}
