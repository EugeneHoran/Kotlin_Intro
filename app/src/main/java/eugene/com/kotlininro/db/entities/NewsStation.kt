package eugene.com.kotlininro.db.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.NonNull
import java.util.*

@Entity(tableName = "news_stations")
data class NewsStation(
        @PrimaryKey @NonNull var id: String,
        var title: String,
        var url: String,
        var newsStationView: NewsStationView,
        var show: Boolean) : Parcelable {

    @Ignore
    constructor(station: NewsStation) : this(
            id = station.id,
            title = station.title,
            url = station.url,
            newsStationView = station.newsStationView,
            show = station.show.not()
    )

    @Ignore
    constructor(
            title: String,
            url: String,
            newsStationView: NewsStationView) : this(
            id = UUID.randomUUID().toString(),
            title = title,
            url = url,
            newsStationView = newsStationView,
            show = true)


    @Ignore
    fun getViewType(): Int {
        return if (show) 0 else 1
    }

    /**
     * Parcelable
     */
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(NewsStationView::class.java.classLoader),
            parcel.readByte() != 0.toByte())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeParcelable(newsStationView, flags)
        parcel.writeByte(if (show) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsStation> {
        override fun createFromParcel(parcel: Parcel): NewsStation {
            return NewsStation(parcel)
        }

        override fun newArray(size: Int): Array<NewsStation?> {
            return arrayOfNulls(size)
        }
    }

}