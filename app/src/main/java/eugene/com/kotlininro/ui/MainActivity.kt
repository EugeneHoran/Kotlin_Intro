package eugene.com.kotlininro.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import eugene.com.kotlininro.R
import eugene.com.kotlininro.ui.common.NavigationController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var navController: NavigationController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = NavigationController(supportFragmentManager)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        if (savedInstanceState == null) {
            navController?.navToFrag(getString(R.string.title_home))
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                navController?.navToFrag(getString(R.string.title_home))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                navController?.navToFrag(getString(R.string.title_dashboard))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                navController?.navToFrag(getString(R.string.title_notifications))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
