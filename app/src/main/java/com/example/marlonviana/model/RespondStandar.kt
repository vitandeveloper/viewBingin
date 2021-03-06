package com.example.marlonviana.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RespondStandar (
    @SerializedName("code")
    @Expose
    var code: Int? = null,
    @SerializedName("message")
    @Expose
    var message: String? = null,
    @SerializedName("data")
    @Expose
    var data: String? = null
)