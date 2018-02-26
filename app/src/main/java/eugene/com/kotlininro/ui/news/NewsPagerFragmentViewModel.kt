package eugene.com.kotlininro.ui.news

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import eugene.com.kotlininro.NewsApplication
import eugene.com.kotlininro.db.dao.NewsDao
import eugene.com.kotlininro.db.entities.NewsStation
import javax.inject.Inject

class NewsPagerFragmentViewModel : ViewModel() {
    @Inject
    lateinit var newsDao: NewsDao

    private val news = MediatorLiveData<List<NewsStation>>()
    private val logos = MutableLiveData<IntArray>()

    init {
        NewsApplication.graph.inject(this)
        news.addSource(newsDao.getSelectedNews(), {
            setupData(it)
        })
    }

    fun getNews(): LiveData<List<NewsStation>> = news

    fun getLogos(): LiveData<IntArray> = logos

    private fun setupData(newsList: List<NewsStation>?) {
        if (newsList == null || newsList.isEmpty()) return
        val logos = IntArray(newsList.size)
        for (i in newsList.indices) logos[i] = newsList[i].newsStationView.logo!!.toInt()
        this.logos.value = logos
        news.value = newsList
    }
}