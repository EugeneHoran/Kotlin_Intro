package eugene.com.kotlininro.model

import com.google.gson.annotations.SerializedName

data class RssResponse(
        @SerializedName("status") val status: String? = null,
        @SerializedName("feed") var feed: Feed? = null,
        @SerializedName("items") var items: List<Item>? = null)
