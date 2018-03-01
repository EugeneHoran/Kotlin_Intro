package eugene.com.kotlininro.dagger.component

import dagger.Component
import eugene.com.kotlininro.dagger.NewsApplicationScope
import eugene.com.kotlininro.dagger.module.ApiModule
import eugene.com.kotlininro.dagger.module.ContextModule
import eugene.com.kotlininro.dagger.module.NetworkModule
import eugene.com.kotlininro.dagger.module.RoomModule
import eugene.com.kotlininro.ui.MainActivity
import eugene.com.kotlininro.ui.news.NewsPagerFragmentViewModel
import eugene.com.kotlininro.ui.news.NewsStationFragmentViewModel
import eugene.com.kotlininro.ui.selector.SelectorFragmentViewModel


@NewsApplicationScope
@Component(modules = [
    ContextModule::class,
    NetworkModule::class,
    ApiModule::class,
    RoomModule::class
])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(newsPagerFragmentViewModel: NewsPagerFragmentViewModel)
    fun inject(newsStationFragmentViewModel: NewsStationFragmentViewModel)
    fun inject(selectorFragmentViewModel: SelectorFragmentViewModel)
}