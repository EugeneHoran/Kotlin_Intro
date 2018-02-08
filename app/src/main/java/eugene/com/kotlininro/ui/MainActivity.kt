package eugene.com.kotlininro.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import eugene.com.kotlininro.R
import eugene.com.kotlininro.ui.common.NavigationController
import eugene.com.kotlininro.ui.rss.NewsCallbacks

class MainActivity : AppCompatActivity(), NewsCallbacks.FragmentCallbacks {
    private var navController: NavigationController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = NavigationController(supportFragmentManager)
        if (savedInstanceState == null) {
            navController?.navToPagerFragment()
        }
    }

    override fun initActionbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        title = null
    }
}
