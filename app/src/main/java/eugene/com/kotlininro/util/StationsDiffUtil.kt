package eugene.com.kotlininro.util

import android.support.v7.util.DiffUtil

import eugene.com.kotlininro.db.entities.NewsStation


class StationsDiffUtil(private val oldList: List<NewsStation>, private val newList: List<NewsStation>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val stationOld = oldList[oldItemPosition]
        val stationNew = newList[newItemPosition]
        return stationOld.id == stationNew.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val stationOld = oldList[oldItemPosition]
        val stationNew = newList[newItemPosition]
        return stationOld.id == stationNew.id && stationOld.show == stationNew.show
    }
}
