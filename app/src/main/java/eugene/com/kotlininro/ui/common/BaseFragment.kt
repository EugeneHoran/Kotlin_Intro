package eugene.com.kotlininro.ui.common

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Window
import eugene.com.kotlininro.R

abstract class BaseFragment : Fragment() {
    abstract fun onCreateFrag(savedInstanceState: Bundle?)

    val isVersion21 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
    var listener: Callbacks.FragmentNavigationCallbacks? = null
    var mainActivity: AppCompatActivity? = null
    var toolbar: Toolbar? = null
    var toggle: ActionBarDrawerToggle? = null
    var window: Window? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as AppCompatActivity
        if (isVersion21) window = activity!!.window
        onCreateFrag(savedInstanceState)
    }

    /**
     * Handle Toolbar/Actionbar
     */
    fun setSupportActionbar(toolbar: Toolbar, initDrawer: Boolean, addToPopBack: Boolean) {
        this.toolbar = toolbar
        mainActivity!!.setSupportActionBar(toolbar)
        mainActivity!!.title = null
        val drawer = mainActivity!!.findViewById<DrawerLayout>(R.id.drawer)
        if (initDrawer) {
            toggle = ActionBarDrawerToggle(mainActivity!!, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
            drawer.addDrawerListener(toggle!!)
            toggle!!.syncState()
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, GravityCompat.START)
        } else {
            drawer.closeDrawer(GravityCompat.START)
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.START)
            if (addToPopBack) {
                toolbar.navigationIcon = ContextCompat.getDrawable(mainActivity!!, R.drawable.ic_arrow_back)
            }
        }
    }

    /**
     * Initiate Navigation Callbacks
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Callbacks.FragmentNavigationCallbacks) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement Callbacks.FragmentNavigationCallbacks")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}