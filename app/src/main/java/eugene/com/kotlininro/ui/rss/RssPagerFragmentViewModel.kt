package eugene.com.kotlininro.ui.rss

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import eugene.com.kotlininro.db.dao.NewsDao
import eugene.com.kotlininro.db.entities.NewsStation

class RssPagerFragmentViewModel(newsDao: NewsDao) : ViewModel() {
    var getNewsStations: LiveData<List<NewsStation>> = newsDao.getNewsStations()
}