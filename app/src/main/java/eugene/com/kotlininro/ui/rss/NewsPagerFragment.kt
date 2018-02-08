package eugene.com.kotlininro.ui.rss

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eugene.com.kotlininro.R
import eugene.com.kotlininro.databinding.FragmentNewsPagerBinding
import eugene.com.kotlininro.db.NewsDatabase
import eugene.com.kotlininro.db.dao.NewsDao
import eugene.com.kotlininro.util.ViewModelFactory

class NewsPagerFragment : Fragment() {

    private lateinit var model: NewsPagerFragmentViewModel
    private lateinit var binding: FragmentNewsPagerBinding
    private lateinit var adapter: NewsPagerAdapter
    private var page: Int = 0

    companion object {
        private const val STATE_PAGER_PAGE = "saved_state_pager_page"
        fun newInstance(): NewsPagerFragment {
            return NewsPagerFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val newsDao: NewsDao = NewsDatabase.getInstance(activity!!).getNewsDao()
        model = ViewModelProviders.of(this, ViewModelFactory(
                NewsPagerFragmentViewModel(newsDao)))[NewsPagerFragmentViewModel::class.java]
        adapter = NewsPagerAdapter(childFragmentManager)
        if (savedInstanceState != null) {
            page = savedInstanceState.getInt(STATE_PAGER_PAGE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_pager, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.pager)
        observeNewsStations(model)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_PAGER_PAGE, binding.pager.currentItem)
        super.onSaveInstanceState(outState)
    }

    private fun observeNewsStations(model: NewsPagerFragmentViewModel) {
        model.getNewsStations.observe(this, Observer {
            if (it != null) {
                adapter.setNewsList(it)
                binding.pager.currentItem = page
            }
        })
    }
}