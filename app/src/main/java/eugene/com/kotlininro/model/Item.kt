package eugene.com.kotlininro.model

import com.google.gson.annotations.SerializedName

data class Item(
        @SerializedName("title") val title: String? = null,
        @SerializedName("pubDate") val pubDate: String? = null,
        @SerializedName("link") val link: String? = null,
        @SerializedName("description") private val description: String? = null,
        @SerializedName("enclosure") private val enclosure: Enclosure? = null,
        @SerializedName("categories") private val categories: List<String>? = null) {

    fun getTestingImageUrl(): String? {
        if (enclosure!!.link == null && enclosure.thumbnail == null) {
            return null
        }
        var url: String? = null
        if (enclosure.thumbnail != null) {
            url = enclosure.thumbnail
        }
        if (enclosure.link != null) {
            url = enclosure.link
        }
        return url
    }

    fun getDescription(): String {
        if (description!!.contains("<")) {
            val des = description.split("<".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            return des[0]
        }
        return description
    }
}
