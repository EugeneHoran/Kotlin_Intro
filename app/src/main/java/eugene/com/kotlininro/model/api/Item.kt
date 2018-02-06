package eugene.com.kotlininro.model.api

import com.google.gson.annotations.SerializedName

data class Item(
        @SerializedName("title") private val title: String? = null,
        @SerializedName("pubDate") private val pubDate: String? = null,
        @SerializedName("link") private val link: String? = null,
        @SerializedName("description") private val description: String? = null,
        @SerializedName("enclosure") private val enclosure: Enclosure? = null,
        @SerializedName("categories") private val categories: List<String>? = null) {

    val imageUrl: String?
        get() {
            var url: String? = null
            if (enclosure!!.link == null && enclosure.thumbnail == null) {
                url = null
            } else {
                if (enclosure.thumbnail != null) {
                    url = enclosure.thumbnail
                }
                if (enclosure.link != null) {
                    url = enclosure.link
                }
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
