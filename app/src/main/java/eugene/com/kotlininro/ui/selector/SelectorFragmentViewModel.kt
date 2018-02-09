package eugene.com.kotlininro.ui.selector

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import eugene.com.kotlininro.db.dao.NewsDao
import eugene.com.kotlininro.db.entities.NewsStation

class SelectorFragmentViewModel(private val newsDao: NewsDao) : ViewModel() {
    fun getNews(): LiveData<List<NewsStation>> = newsDao.getNews()
}