package eugene.com.kotlininro.ui.rss

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import eugene.com.kotlininro.api.NewsApi
import eugene.com.kotlininro.db.entities.NewsStation
import eugene.com.kotlininro.model.RssResponse
import eugene.com.livelib.ApiResponse

class NewsStationFragmentViewModel(newsStation: NewsStation) : ViewModel() {
    private val news = MediatorLiveData<ApiResponse<RssResponse>>()
    private val loading = MutableLiveData<Boolean>()

    init {
        loading.value = true
        news.addSource(NewsApi.create().getNews(newsStation.url), {
            loading.value = false
            news.value = it
        })
    }

    fun getNews(): LiveData<ApiResponse<RssResponse>> {
        return news
    }

    fun getLoading(): LiveData<Boolean> {
        return loading
    }
}