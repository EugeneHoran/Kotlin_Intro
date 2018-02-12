package eugene.com.kotlininro.ui.selector

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import eugene.com.kotlininro.databinding.RecyclerSelectorSelectedItemBinding
import eugene.com.kotlininro.databinding.RecyclerSelectorUnselectedItemBinding
import eugene.com.kotlininro.db.entities.NewsStation
import eugene.com.kotlininro.util.StationsDiffUtil

class SelectorRecyclerAdapter(var model: SelectorFragmentViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_SELECTED = 0
        const val TYPE_UNSELECTED = 1
    }

    private var stationList = mutableListOf<NewsStation>()

    fun setItems(itemList: List<NewsStation>) {
        val diffResult = DiffUtil.calculateDiff(StationsDiffUtil(stationList, itemList))
        stationList.clear()
        stationList.addAll(itemList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int): Int = stationList[position].getViewType()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        return when (viewType) {
            TYPE_SELECTED -> SelectedViewHolder(RecyclerSelectorSelectedItemBinding.inflate(LayoutInflater.from(parent!!.context), parent, false))
            TYPE_UNSELECTED -> UnselectedViewHolder(RecyclerSelectorUnselectedItemBinding.inflate(LayoutInflater.from(parent!!.context), parent, false))
            else -> null
        }
    }

    override fun getItemCount(): Int = stationList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (getItemViewType(position)) {
            TYPE_SELECTED -> {
                val selectedHolder = holder as SelectedViewHolder
                selectedHolder.bindView(stationList[position], model)
            }
            TYPE_UNSELECTED -> {
                val unselectedHolder = holder as UnselectedViewHolder
                unselectedHolder.bindView(stationList[position], model)
            }
        }
    }

    class SelectedViewHolder(private var binding: RecyclerSelectorSelectedItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: SelectorFragmentViewModel
        fun bindView(station: NewsStation, model: SelectorFragmentViewModel) {
            this.model = model
            binding.news = station
            binding.holder = this
        }

        fun itemClicked(station: NewsStation) {
            model.itemClicked(station)
        }
    }

    class UnselectedViewHolder(private var binding: RecyclerSelectorUnselectedItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: SelectorFragmentViewModel
        fun bindView(station: NewsStation, model: SelectorFragmentViewModel) {
            this.model = model
            binding.news = station
            binding.holder = this
        }

        fun itemClicked(station: NewsStation) {
            model.itemClicked(station)
        }
    }
}