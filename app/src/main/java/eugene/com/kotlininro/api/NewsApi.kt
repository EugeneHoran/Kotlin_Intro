package eugene.com.kotlininro.api

import android.arch.lifecycle.LiveData
import eugene.com.kotlininro.BuildConfig
import eugene.com.kotlininro.model.api.RssResponse
import eugene.com.livelib.ApiResponse
import eugene.com.livelib.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface NewsApi {

    @GET("v1/api.json")
    fun getNews(@Query("rss_url") url: String): LiveData<ApiResponse<RssResponse>>

    companion object Factory {

        fun create(): NewsApi {
            return retrofit().create(NewsApi::class.java)
        }

        private fun retrofit(): Retrofit {
            val builder = Retrofit.Builder()
            builder.baseUrl(BuildConfig.NEWS_URL)
            builder.addConverterFactory(GsonConverterFactory.create())
            builder.addCallAdapterFactory(LiveDataCallAdapterFactory())
            builder.client(http3Client())
            return builder.build()
        }

        private fun http3Client(): OkHttpClient {
            val builder = OkHttpClient.Builder()
            builder.connectTimeout(BuildConfig.TIMEOUT, TimeUnit.SECONDS)
            builder.writeTimeout(BuildConfig.TIMEOUT, TimeUnit.SECONDS)
            builder.readTimeout(BuildConfig.TIMEOUT, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                builder.addInterceptor(logger())
            }
            return builder.build()
        }

        private fun logger(): HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.HEADERS
            return interceptor
        }
    }
}