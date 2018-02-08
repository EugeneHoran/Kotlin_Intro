package eugene.com.kotlininro.ui.rss

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.graphics.ColorUtils
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import eugene.com.kotlininro.R
import eugene.com.kotlininro.databinding.FragmentNewsPagerBinding
import eugene.com.kotlininro.db.NewsDatabase
import eugene.com.kotlininro.db.entities.NewsStationView
import eugene.com.kotlininro.util.ViewModelFactory

class NewsPagerFragment : Fragment(), AppBarLayout.OnOffsetChangedListener, TabLayout.OnTabSelectedListener {
    companion object {
        private const val STATE_PAGER_PAGE = "saved_state_pager_page"
        private const val STATE_APP_BAR_EXPANDED = "saved_state_app_bar_expanded"
        fun newInstance(): NewsPagerFragment {
            return NewsPagerFragment()
        }
    }

    private var listener: NewsCallbacks.FragmentCallbacks? = null
    private lateinit var window: Window
    private lateinit var model: NewsPagerFragmentViewModel
    private lateinit var binding: FragmentNewsPagerBinding
    private lateinit var adapter: NewsPagerAdapter
    private var newsPagerHelper: NewsPagerFragmentHelper? = null
    private var page: Int = 0
    private var appBarIsExpanded = true
    private var logos: IntArray? = null
    internal var swipeRightOffset: Float = 0.toFloat()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) window = activity!!.window
        newsPagerHelper = NewsPagerFragmentHelper()
        model = ViewModelProviders.of(this, ViewModelFactory(
                NewsPagerFragmentViewModel(
                        NewsDatabase.getInstance(activity!!)
                                .getNewsDao())))[NewsPagerFragmentViewModel::class.java]
        adapter = NewsPagerAdapter(childFragmentManager)
        if (savedInstanceState != null) {
            page = savedInstanceState.getInt(STATE_PAGER_PAGE)
            appBarIsExpanded = savedInstanceState.getBoolean(STATE_APP_BAR_EXPANDED, true)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_pager, container, false)
        listener!!.initActionbar(binding.toolbar)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.pager)
        binding.pager.addOnPageChangeListener(pageChangeListener)
        binding.tabs.addOnTabSelectedListener(this)
        binding.appBar.addOnOffsetChangedListener(this)
        observeNewsStations(model)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_PAGER_PAGE, binding.pager.currentItem)
        outState.putBoolean(STATE_APP_BAR_EXPANDED, appBarIsExpanded)
        super.onSaveInstanceState(outState)
    }

    /**
     * Observe data changes
     * API call
     * Logos for Toolbar
     */
    private fun observeNewsStations(model: NewsPagerFragmentViewModel) {
        model.getNews().observe(this, Observer {
            if (it != null) {
                newsPagerHelper!!.setList(it)
                adapter.setNewsList(it)
                binding.pager.currentItem = page
                binding.appBar.setExpanded(appBarIsExpanded)
            }
        })
        model.getLogos().observe(this, Observer { logos = it })
    }

    /**
     * Check for AppBar expanded or collapsed
     * Save State
     */
    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        appBarIsExpanded = verticalOffset == 0
    }

    /**
     * Tab selected
     * Go to page without animation
     */
    override fun onTabSelected(tab: TabLayout.Tab) {
        binding.pager.setCurrentItem(tab.position, false)
    }

    // TODO Clean and optimize
    private val pageChangeListener = object : ViewPager.SimpleOnPageChangeListener() {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            val isSwipeRight = position + positionOffset > swipeRightOffset
            swipeRightOffset = position + positionOffset
            val scrollPastHalf = positionOffset >= 0.5f
            if (positionOffset == 0f) {
                binding.toolbarLogo.setImageResource(logos!![position])
            } else {
                if (scrollPastHalf) {
                    if (isSwipeRight) {
                        binding.toolbarLogo.setImageResource(logos!![position + 1])
                    }
                    binding.toolbarLogo.alpha = Math.abs(positionOffset) * 2 - 1.0f
                } else {
                    if (!isSwipeRight) {
                        binding.toolbarLogo.setImageResource(logos!![position])
                    }
                    binding.toolbarLogo.alpha = 1.0f - Math.abs(positionOffset) * 2
                }
            }
            initColorChange(newsPagerHelper!!.getColorsFormatted(adapter.count, position, positionOffset)!!)
        }

        override fun onPageScrollStateChanged(state: Int) {
            binding.appBar.setExpanded(true, true)
        }
    }

    /**
     * Handle views based on pager scroll
     */
    private fun initColorChange(newsLogoAndColors: NewsStationView) {
        if (activity != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = newsLogoAndColors.colorPrimaryDark!!
        }
        listener!!.navIconColor(newsLogoAndColors.colorAccent!!)
        binding.appBar.setBackgroundColor(newsLogoAndColors.colorPrimary!!)
        binding.tabs.setSelectedTabIndicatorColor(newsLogoAndColors.colorAccent!!)
        binding.tabs.setTabTextColors(ColorUtils.setAlphaComponent(newsLogoAndColors.colorAccent!!, 180), newsLogoAndColors.colorAccent!!)
    }

    /**
     * Init and Remove listener
     */
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is NewsCallbacks.FragmentCallbacks) {
            listener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentCallbacks")
        }
    }

    override fun onDetach() {
//        listener!!.detach()
        super.onDetach()
        listener = null
    }

    /**
     * Not Used
     */
    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }
}