package eugene.com.kotlininro.ui

import android.databinding.DataBindingUtil
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import eugene.com.kotlininro.R
import eugene.com.kotlininro.databinding.ActivityMainBinding
import eugene.com.kotlininro.ui.common.NavigationController
import eugene.com.kotlininro.ui.rss.NewsCallbacks

class MainActivity : AppCompatActivity(), NewsCallbacks.FragmentCallbacks {
    private lateinit var binding: ActivityMainBinding
    private var navController: NavigationController? = null
    private var toggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = NavigationController(supportFragmentManager)
        if (savedInstanceState == null) {
            navController?.navToPagerFragment()
        }
    }

    override fun onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun initActionbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        title = null
        toggle = ActionBarDrawerToggle(this, binding.drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawer.addDrawerListener(toggle!!)
        toggle!!.syncState()
    }

    override fun navIconColor(color: Int) {
        if (toggle != null) {
            toggle!!.drawerArrowDrawable.mutate().setColorFilter(color, PorterDuff.Mode.MULTIPLY)
        }
    }
}
