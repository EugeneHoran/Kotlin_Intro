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
        var newsStationView: NewsStationView) : Parcelable {

    @Ignore
    constructor(
            title: String,
            url: String,
            newsStationView: NewsStationView) : this(
            id = UUID.randomUUID().toString(),
            title = title,
            url = url,
            newsStationView = newsStationView)

    @Ignore
    constructor(newsStation: NewsStation) : this(
            id = newsStation.id,
            title = newsStation.title,
            url = newsStation.url,
            newsStationView = newsStation.newsStationView)

    /**
     * Parcelable
     */
    override fun describeContents(): Int {
        return 0
    }

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(NewsStationView::class.java.classLoader))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeParcelable(newsStationView, flags)
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