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
        var show: Boolean,
        var title: String,
        var newsStationLinks: List<NewsStationLink>,
        var newsStationView: NewsStationView) : Parcelable {

    @Ignore
    constructor(
            title: String,
            newsStationLinks: List<NewsStationLink>,
            newsStationView: NewsStationView) : this(
            id = UUID.randomUUID().toString(),
            show = true,
            title = title,
            newsStationLinks = newsStationLinks,
            newsStationView = newsStationView)

    @Ignore
    constructor(newsStation: NewsStation) : this(
            id = newsStation.id,
            show = newsStation.show.not(),
            title = newsStation.title,
            newsStationLinks = newsStation.newsStationLinks,
            newsStationView = newsStation.newsStationView)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeByte(if (show) 1 else 0)
        parcel.writeString(title)
        parcel.writeTypedList(newsStationLinks)
        parcel.writeParcelable(newsStationView, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readString(),
            parcel.createTypedArrayList(NewsStationLink),
            parcel.readParcelable(NewsStationView::class.java.classLoader))

    companion object CREATOR : Parcelable.Creator<NewsStation> {
        override fun createFromParcel(parcel: Parcel): NewsStation {
            return NewsStation(parcel)
        }

        override fun newArray(size: Int): Array<NewsStation?> {
            return arrayOfNulls(size)
        }
    }

}