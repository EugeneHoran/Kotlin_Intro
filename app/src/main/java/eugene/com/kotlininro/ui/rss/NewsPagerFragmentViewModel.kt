package eugene.com.kotlininro.ui.rss

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import eugene.com.kotlininro.db.dao.NewsDao
import eugene.com.kotlininro.db.entities.NewsStation

class NewsPagerFragmentViewModel(newsDao: NewsDao) : ViewModel() {
    private val news = MediatorLiveData<List<NewsStation>>()
    private val logos = MutableLiveData<IntArray>()

    init {
        news.addSource(newsDao.getNews(), {
            setupData(it)
        })
    }

    fun getNews(): LiveData<List<NewsStation>> {
        return news
    }

    fun getLogos(): LiveData<IntArray> {
        return logos
    }

    private fun setupData(newsList: List<NewsStation>?) {
        if (newsList == null || newsList.isEmpty()) return
        val logos = IntArray(newsList.size)
        for (i in newsList.indices) logos[i] = newsList[i].newsStationView.logo!!.toInt()
        this.logos.value = logos
        news.value = newsList
    }
}