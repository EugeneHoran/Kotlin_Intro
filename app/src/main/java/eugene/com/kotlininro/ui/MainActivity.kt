package eugene.com.kotlininro.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import eugene.com.kotlininro.R
import eugene.com.kotlininro.databinding.ActivityMainBinding
import eugene.com.kotlininro.ui.common.Callbacks
import eugene.com.kotlininro.ui.common.NavigationController

class MainActivity : AppCompatActivity(), Callbacks.NavigationControllerCallbacks, Callbacks.FragmentNavigationCallbacks {
    companion object {
        private const val STATE_FRAG_IN_POP_BACK = "state_frag_has_nav_icon"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavigationController
    private var fragmentInPopBack: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = NavigationController(supportFragmentManager, this)
        if (savedInstanceState == null) {
            navToNewsFragment()
        } else {
            fragmentInPopBack = savedInstanceState.getBoolean(STATE_FRAG_IN_POP_BACK, false)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState!!.putBoolean(STATE_FRAG_IN_POP_BACK, fragmentInPopBack!!)
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        when {
            fragmentInPopBack!! -> navToNewsFragment()
            binding.drawer.isDrawerOpen(GravityCompat.START) -> binding.drawer.closeDrawer(GravityCompat.START)
            else -> super.onBackPressed()
        }
    }

    /**
     * Fragment Navigation Callbacks
     */
    override fun navToSelectorFragment(popBackEnabled: Boolean) {
        navController.navToSelectorFragment(popBackEnabled)
    }

    override fun navToNewsFragment() {
        navController.navToNewsFragment()
    }

    /**
     * NavigationController Callbacks
     */
    override fun fragmentPopBackEnabled(popBackEnabled: Boolean) {
        fragmentInPopBack = popBackEnabled
    }
}
