package eugene.com.kotlininro.model.api

import com.google.gson.annotations.SerializedName

data class Enclosure(
        @SerializedName("link") var link: String? = null,
        @SerializedName("thumbnail") var thumbnail: String? = null)
