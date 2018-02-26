package eugene.com.kotlininro.api

import android.arch.lifecycle.LiveData
import eugene.com.kotlininro.model.RssResponse
import eugene.com.livelib.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v1/api.json")
    fun getNews(@Query("rss_url") url: String): LiveData<ApiResponse<RssResponse>>
}