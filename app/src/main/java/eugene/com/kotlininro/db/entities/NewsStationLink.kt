package eugene.com.kotlininro.db.entities

import android.os.Parcel
import android.os.Parcelable


data class NewsStationLink(
        var position: Int? = null,
        var title: String? = null,
        var url: String? = null,
        var primary: Boolean? = null,
        var checked: Boolean? = null) : Parcelable {

    constructor(position: Int?,
                title: String?,
                url: String?,
                primary: Boolean?) :
            this(
                    position,
                    title,
                    url,
                    primary,
                    true)

    constructor(position: Int?,
                title: String?,
                url: String?) :
            this(
                    position,
                    title,
                    url,
                    false,
                    true)

    /**
     * To String
     */
    override fun toString(): String {
        return "NewsStationLink(position=$position, title=$title, url=$url, primary=$primary, checked=$checked)"
    }

    /**
     * Parcelable
     */
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
            parcel.readValue(Boolean::class.java.classLoader) as? Boolean)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(position)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeValue(primary)
        parcel.writeValue(checked)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsStationLink> {
        override fun createFromParcel(parcel: Parcel): NewsStationLink {
            return NewsStationLink(parcel)
        }

        override fun newArray(size: Int): Array<NewsStationLink?> {
            return arrayOfNulls(size)
        }
    }

}