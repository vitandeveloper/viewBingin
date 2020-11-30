package com.example.marlonviana.model
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Events (
    @SerializedName("available")
    @Expose
    var available: Int? = null,

    @SerializedName("collectionURI")
    @Expose
    var collectionURI: String? = null,

    @SerializedName("returned")
    @Expose
    var returned: Int? = null
)

