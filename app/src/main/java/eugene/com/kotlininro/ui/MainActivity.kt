package eugene.com.kotlininro.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import eugene.com.kotlininro.R
import eugene.com.kotlininro.ui.common.NavigationController

class MainActivity : AppCompatActivity() {
    private var navController: NavigationController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = NavigationController(supportFragmentManager)
        if (savedInstanceState == null) {
            navController?.navToPagerFragment()
        }
    }
}
