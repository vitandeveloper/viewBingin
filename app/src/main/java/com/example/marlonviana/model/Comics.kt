package com.example.marlonviana.model

import android.content.ClipData.Item
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Comics (
    @SerializedName("available")
    @Expose
    var available: Int? = null,

    @SerializedName("collectionURI")
    @Expose
    var collectionURI: String? = null,

    @SerializedName("items")
    @Expose
    var items: MutableList<ItemComics>? = null,

    @SerializedName("returned")
    @Expose
    var returned: Int? = null
)