package eugene.com.kotlininro.ui.selector

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.view.*
import eugene.com.kotlininro.R
import eugene.com.kotlininro.databinding.FragmentSelectorBinding
import eugene.com.kotlininro.db.NewsDatabase
import eugene.com.kotlininro.ui.common.BaseFragment
import eugene.com.kotlininro.util.ViewModelFactory


class SelectorFragment : BaseFragment() {
    companion object {
        private const val ARGS_SHOW_NAVIGATION = "args_show_navigation"
        fun newInstance(addToPopBack: Boolean): SelectorFragment {
            val fragment = SelectorFragment()
            val args = Bundle()
            args.putBoolean(ARGS_SHOW_NAVIGATION, addToPopBack)
            fragment.arguments = args
            return fragment
        }
    }

    private var addToPopBack: Boolean = false
    private lateinit var model: SelectorFragmentViewModel
    private lateinit var adapter: SelectorRecyclerAdapter
    private lateinit var binding: FragmentSelectorBinding

    override fun onCreateFrag(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        initStatus()
        if (arguments != null) {
            addToPopBack = arguments!!.getBoolean(ARGS_SHOW_NAVIGATION, false)
        }

        model = ViewModelProviders.of(this,
                ViewModelFactory(SelectorFragmentViewModel(
                        NewsDatabase.getInstance(activity!!).getNewsDao())))[SelectorFragmentViewModel::class.java]
        adapter = SelectorRecyclerAdapter(model)
    }

    @SuppressLint("NewApi")
    private fun initStatus() {
        if (isVersion21 && window != null) window!!.statusBarColor = ContextCompat.getColor(activity!!, R.color.colorPrimaryDark)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_selector, container, false)
        setSupportActionbar(binding.toolbar, false, addToPopBack)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        binding.recycler.adapter = adapter
        observeNewsSources(model)
    }

    private fun observeNewsSources(model: SelectorFragmentViewModel) {
        model.getNews().observe(this, Observer {
            if (it != null) {
                adapter.setItems(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_selector, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_continue -> {
                listener!!.navToNewsFragment()
                true
            }
            android.R.id.home -> {
                listener!!.navToNewsFragment()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}