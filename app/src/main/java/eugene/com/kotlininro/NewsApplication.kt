package eugene.com.kotlininro

import android.app.Application
import eugene.com.kotlininro.dagger.component.AppComponent
import eugene.com.kotlininro.dagger.component.DaggerAppComponent
import eugene.com.kotlininro.dagger.module.ContextModule

class NewsApplication : Application() {

    companion object {
        @JvmStatic
        lateinit var graph: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        graph = DaggerAppComponent.builder()
                .contextModule(ContextModule(this))
                .build()
    }
}