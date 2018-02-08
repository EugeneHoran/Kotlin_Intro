package eugene.com.kotlininro.ui.rss

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eugene.com.kotlininro.R
import eugene.com.kotlininro.databinding.FragmentNewsStationBinding
import eugene.com.kotlininro.db.entities.NewsStation
import eugene.com.kotlininro.util.Constants
import eugene.com.kotlininro.util.ViewModelFactory

class NewsStationFragment : Fragment() {
    private lateinit var newsStation: NewsStation
    private lateinit var model: NewsStationFragmentViewModel
    private lateinit var binding: FragmentNewsStationBinding
    private var adapter: NewsStationRecyclerAdapter = NewsStationRecyclerAdapter()

    companion object {
        fun newInstance(newsStation: NewsStation): NewsStationFragment {
            val fragment = NewsStationFragment()
            val args = Bundle()
            args.putParcelable(Constants.ARGS_NEWS_STATION, newsStation)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            newsStation = arguments!!.getParcelable(Constants.ARGS_NEWS_STATION)
            model = ViewModelProviders.of(this,
                    ViewModelFactory(NewsStationFragmentViewModel(newsStation)))[NewsStationFragmentViewModel::class.java]
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_station, container, false)
        binding.recycler.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        if (savedInstanceState == null) {
            binding.isDataLoading = true
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = adapter
        observeNews(model)
    }

    private fun observeNews(model: NewsStationFragmentViewModel) {
        model.getNews().observe(this, Observer {
            if (it?.body?.items != null) {
                binding.isDataLoading = false
                adapter.setItems(it.body?.items!!)
            }
        })
    }
}