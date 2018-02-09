package eugene.com.kotlininro.ui.rss

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import eugene.com.kotlininro.databinding.RecyclerNewsItemBinding
import eugene.com.kotlininro.model.Item
import eugene.com.kotlininro.ui.common.Callbacks

class NewsStationRecyclerAdapter(private var callbacks: Callbacks.AdapterCallbacks) :
        RecyclerView.Adapter<NewsStationRecyclerAdapter.NewsViewHolder>() {

    private var newsItems = mutableListOf<Item>()

    fun setItems(newsItems: List<Item>) {
        this.newsItems.clear()
        this.newsItems.addAll(newsItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder = NewsViewHolder(
            RecyclerNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) = holder
            .bindNews(newsItems[position], callbacks)

    override fun getItemCount() = newsItems.size

    class NewsViewHolder(private var binding: RecyclerNewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindNews(item: Item, listener: Callbacks.AdapterCallbacks) {
            binding.listener = listener
            binding.item = item
        }
    }
}