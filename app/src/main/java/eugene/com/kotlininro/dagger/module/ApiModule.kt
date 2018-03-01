package eugene.com.kotlininro.dagger.module

import dagger.Module
import dagger.Provides
import eugene.com.kotlininro.BuildConfig
import eugene.com.kotlininro.api.NewsApi
import eugene.com.kotlininro.dagger.NewsApplicationScope
import eugene.com.livelib.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [NetworkModule::class])
class ApiModule {

    @Provides
    @NewsApplicationScope
    fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.NEWS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @NewsApplicationScope
    fun newsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }
}