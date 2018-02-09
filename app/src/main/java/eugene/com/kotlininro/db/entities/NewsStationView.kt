package eugene.com.kotlininro.db.entities

import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes

data class NewsStationView(
        @DrawableRes var logo: Int? = null,
        @ColorInt var colorPrimary: Int? = null,
        @ColorInt var colorPrimaryDark: Int? = null,
        @ColorInt var colorAccent: Int? = null,
        @ColorInt var colorText: Int? = null) : Parcelable {

    constructor(
            logo: Int? = null,
            colors: IntArray? = null) :
            this(
                    logo,
                    colors?.get(0),
                    colors?.get(1),
                    colors?.get(2),
                    colors?.get(3))

    constructor(
            colorPrimary: Int?,
            colorPrimaryDark: Int?,
            colorAccent: Int?) :
            this(
                    null,
                    colorPrimary,
                    colorPrimaryDark,
                    colorAccent,
                    null)


    /**
     * Parcelable
     */
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(logo)
        parcel.writeValue(colorPrimary)
        parcel.writeValue(colorPrimaryDark)
        parcel.writeValue(colorAccent)
        parcel.writeValue(colorText)
    }

    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int)

    companion object CREATOR : Parcelable.Creator<NewsStationView> {
        override fun createFromParcel(parcel: Parcel): NewsStationView {
            return NewsStationView(parcel)
        }

        override fun newArray(size: Int): Array<NewsStationView?> {
            return arrayOfNulls(size)
        }
    }
}