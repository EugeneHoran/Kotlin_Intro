package eugene.com.kotlininro.ui

import android.content.Context
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import eugene.com.kotlininro.R
import eugene.com.kotlininro.databinding.ActivityMainBinding
import eugene.com.kotlininro.kotlin.edit
import eugene.com.kotlininro.ui.common.Callbacks
import eugene.com.kotlininro.ui.common.NavigationController

class MainActivity : AppCompatActivity(), Callbacks.NavigationControllerCallbacks, Callbacks.FragmentNavigationCallbacks {
    companion object {
        private const val STATE_FRAG_IN_POP_BACK = "state_frag_has_nav_icon"
        private const val PREF_SHOW_SELECTOR = "prefs_show_selector_fragment"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavigationController
    private lateinit var prefs: SharedPreferences
    private var fragmentInPopBack: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = NavigationController(supportFragmentManager, this)
        prefs = getPreferences(Context.MODE_PRIVATE)
        if (savedInstanceState == null) {
            if (!prefs.getBoolean(PREF_SHOW_SELECTOR, false)) {
                navToSelectorFragment(false)
            } else {
                navToNewsFragment()
            }
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
        prefs.edit {
            putBoolean(PREF_SHOW_SELECTOR, true)
        }
        navController.navToNewsFragment()
    }

    /**
     * NavigationController Callbacks
     */
    override fun fragmentPopBackEnabled(popBackEnabled: Boolean) {
        fragmentInPopBack = popBackEnabled
    }
}
