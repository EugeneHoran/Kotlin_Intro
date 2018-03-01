package eugene.com.kotlininro.dagger.module

import android.content.Context
import dagger.Module
import dagger.Provides
import eugene.com.kotlininro.BuildConfig
import eugene.com.kotlininro.dagger.NewsApplicationScope
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File

@Module(includes = [ContextModule::class])
class NetworkModule {

    /**
     * Cache File
     */
    @Provides
    @NewsApplicationScope
    fun cacheFile(context: Context): File {
        return File(context.cacheDir, "okhttp3_cache")
    }

    /**
     * Cache
     */
    @Provides
    @NewsApplicationScope
    fun cache(cacheFile: File): Cache {
        return Cache(cacheFile, 10 * 1000 * 1000) // 10MB Cache
    }

    /**
     * Logging Interceptor
     */
    @Provides
    @NewsApplicationScope
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.HEADERS else
                    HttpLoggingInterceptor.Level.NONE)
    }

    /**
     * OkHttp3 client
     */
    @Provides
    @NewsApplicationScope
    fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .build()
    }
}