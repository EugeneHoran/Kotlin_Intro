package eugene.com.kotlininro.api

import eugene.com.kotlininro.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

interface NewsApi {

    companion object Factory {
        private val TIMEOUT: Long = 15

        fun create(): NewsApi {
            return retrofit().create(NewsApi::class.java)
        }

        private fun retrofit(): Retrofit {
            val builder = Retrofit.Builder()
            builder.baseUrl(BuildConfig.NEWS_URL)
            builder.addConverterFactory(GsonConverterFactory.create())
//            builder.addCallAdapterFactory(new LiveDataCallAdapterFactory())
            builder.client(http3Client())
            return builder.build()
        }

        private fun http3Client(): OkHttpClient {
            val builder = OkHttpClient.Builder()
            builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            builder.writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            builder.readTimeout(TIMEOUT, TimeUnit.SECONDS)
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