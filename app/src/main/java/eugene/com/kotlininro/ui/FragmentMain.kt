package eugene.com.kotlininro.ui

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
import eugene.com.kotlininro.databinding.FragmentMainBinding
import eugene.com.kotlininro.util.Constants
import eugene.com.kotlininro.util.ViewModelFactory


class FragmentMain : Fragment() {
    private val url: String = "http://rss.cnn.com/rss/cnn_latest.rss"
    private var name: String? = null
    private lateinit var binding: FragmentMainBinding
    private lateinit var model: FragmentMainViewModel

    companion object {
        fun newInstance(text: String): FragmentMain {
            val fragment = FragmentMain()
            val args = Bundle()
            args.putString(Constants.ARGS_STATION, text)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            name = arguments!!.getString(Constants.ARGS_STATION)
        }
        model = ViewModelProviders.of(
                this,
                ViewModelFactory(FragmentMainViewModel(url)))[FragmentMainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNewsResponse(model)
    }

    private fun observeNewsResponse(model: FragmentMainViewModel) {
        model.newsResponse.observe(this, Observer {
            if (it?.body?.items != null) {
                Log.e("Testing", "" + it.body?.items?.size)
            }
        })
    }
}