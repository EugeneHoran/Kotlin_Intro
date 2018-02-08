package eugene.com.kotlininro.ui.rss

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import eugene.com.kotlininro.api.NewsApi
import eugene.com.kotlininro.db.entities.NewsStation
import eugene.com.kotlininro.model.RssResponse
import eugene.com.livelib.ApiResponse

class NewsStationFragmentViewModel(private var newsStation: NewsStation) : ViewModel() {
    fun getNews(): LiveData<ApiResponse<RssResponse>> = NewsApi.create().getNews(newsStation.newsStationLinks[0].url!!)
}