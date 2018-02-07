package eugene.com.kotlininro.ui.rss

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eugene.com.kotlininro.R
import eugene.com.kotlininro.databinding.FragmentRssPagerBinding
import eugene.com.kotlininro.db.NewsDatabase
import eugene.com.kotlininro.db.dao.NewsDao
import eugene.com.kotlininro.util.ViewModelFactory

class RssPagerFragment : Fragment() {
    private lateinit var model: RssPagerFragmentViewModel
    private lateinit var binding: FragmentRssPagerBinding

    companion object {
        fun newInstance(): RssPagerFragment {
            return RssPagerFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val newsDao: NewsDao = NewsDatabase.getInstance(activity!!).getNewsDao()
        model = ViewModelProviders.of(this,
                ViewModelFactory(
                        RssPagerFragmentViewModel(newsDao)))[RssPagerFragmentViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rss_pager, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNewsStations(model)
    }

    private fun observeNewsStations(model: RssPagerFragmentViewModel) {
        model.getNewsStations.observe(this, Observer {
            if (it != null) {
                Log.e("Testing", it[0].newsStationLinks?.get(0)?.url)
            }
        })
    }
}