package eugene.com.kotlininro.ui.selector

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import eugene.com.kotlininro.NewsApplication
import eugene.com.kotlininro.db.dao.NewsDao
import eugene.com.kotlininro.db.entities.NewsStation
import eugene.com.kotlininro.util.ioThread
import javax.inject.Inject

class SelectorFragmentViewModel : ViewModel() {
    @Inject
    lateinit var newsDao: NewsDao
    var newsList = MediatorLiveData<List<NewsStation>>()

    init {
        NewsApplication.graph.inject(this)
        newsList.addSource(newsDao.getNews(), Observer {
            newsList.value = it
        })
    }

    fun getNews(): LiveData<List<NewsStation>> = newsList

    fun itemClicked(station: NewsStation) {
        ioThread {
            newsDao.update(NewsStation(station))
        }
    }
}