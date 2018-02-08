package eugene.com.kotlininro.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import eugene.com.kotlininro.api.NewsApi
import eugene.com.kotlininro.model.RssResponse
import eugene.com.livelib.ApiResponse

class FragmentMainViewModel(url: String) : ViewModel() {
    var newsResponse: LiveData<ApiResponse<RssResponse>> = NewsApi.create().getNews(url)
}