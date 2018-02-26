package eugene.com.kotlininro.ui.news

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eugene.com.kotlininro.R
import eugene.com.kotlininro.databinding.FragmentNewsStationBinding
import eugene.com.kotlininro.db.entities.NewsStation
import eugene.com.kotlininro.ui.common.Callbacks
import eugene.com.kotlininro.util.ViewModelFactory

class NewsStationFragment : Fragment(), Callbacks.AdapterCallbacks {
    private lateinit var newsStation: NewsStation
    private lateinit var model: NewsStationFragmentViewModel
    private lateinit var binding: FragmentNewsStationBinding
    private var adapter: NewsStationRecyclerAdapter = NewsStationRecyclerAdapter(this)

    companion object {
        const val ARGS_NEWS_STATION = "args_news_station"
        fun newInstance(newsStation: NewsStation): NewsStationFragment {
            val fragment = NewsStationFragment()
            val args = Bundle()
            args.putParcelable(ARGS_NEWS_STATION, newsStation)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            newsStation = arguments!!.getParcelable(ARGS_NEWS_STATION)
            model = ViewModelProviders.of(this,
                    ViewModelFactory(NewsStationFragmentViewModel(newsStation)))[NewsStationFragmentViewModel::class.java]
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_station, container, false)
        binding.recycler.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
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
                adapter.setItems(it.body?.items!!)
            }
        })
        model.getLoading().observe(this, Observer { binding.isDataLoading = it })
    }

    override fun onNewsItemClicked(url: String) {
        if (activity != null) {
            val builder = CustomTabsIntent.Builder()
            builder.setToolbarColor(newsStation.newsStationView.colorPrimary!!)
            builder.addDefaultShareMenuItem()
            builder.setShowTitle(true)
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(activity!!, Uri.parse(url))
        }
    }
}