package eugene.com.kotlininro.dagger.component

import dagger.Component
import eugene.com.kotlininro.dagger.module.ApiModule
import eugene.com.kotlininro.dagger.module.RoomModule
import eugene.com.kotlininro.ui.news.NewsPagerFragmentViewModel
import eugene.com.kotlininro.ui.news.NewsStationFragmentViewModel
import eugene.com.kotlininro.ui.selector.SelectorFragmentViewModel
import javax.inject.Singleton


@Singleton
@Component(modules = [
    ApiModule::class,
    RoomModule::class
])
interface AppComponent {
    fun inject(newsPagerFragmentViewModel: NewsPagerFragmentViewModel)
    fun inject(newsStationFragmentViewModel: NewsStationFragmentViewModel)
    fun inject(selectorFragmentViewModel: SelectorFragmentViewModel)
}