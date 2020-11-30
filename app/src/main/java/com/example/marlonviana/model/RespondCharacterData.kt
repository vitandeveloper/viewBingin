package com.example.marlonviana.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RespondCharacterData(
    @SerializedName("code")
    @Expose
    var code: Int? = null,
    @SerializedName("message")
    @Expose
    var message: String? = null
)