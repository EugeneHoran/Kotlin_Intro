package eugene.com.kotlininro.dagger.module

import android.content.Context
import dagger.Module
import dagger.Provides
import eugene.com.kotlininro.dagger.NewsApplicationScope

@Module
class ContextModule(val context: Context) {

    @Provides
    @NewsApplicationScope
    fun context(): Context {
        return context
    }
}