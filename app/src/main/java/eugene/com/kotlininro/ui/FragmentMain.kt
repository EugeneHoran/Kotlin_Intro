package eugene.com.kotlininro.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eugene.com.kotlininro.util.Constants
import eugene.com.kotlininro.R
import eugene.com.kotlininro.databinding.FragmentMainBinding


class FragmentMain : Fragment() {
    private lateinit var name: String
    private lateinit var binding: FragmentMainBinding
    private lateinit var model: FragmentMainViewModel

    companion object {
        fun newInstance(text: String): FragmentMain {
            val fragment = FragmentMain()
            val args = Bundle()
            Bundle().putString(Constants.ARGS_TEXT, text)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = arguments!!.getString(Constants.ARGS_TEXT)
        model = ViewModelProviders.of(this)[FragmentMainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.text = name
        observeString(model)
    }

    private fun observeString(model: FragmentMainViewModel) {
        model.mutableLiveData.observe(this, Observer<String> {
            binding.text = it
        })
    }
}