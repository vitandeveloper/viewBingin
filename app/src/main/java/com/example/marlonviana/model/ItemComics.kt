package com.example.marlonviana.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class ItemComics (
    @SerializedName("resourceURI")
    @Expose
    var resourceURI: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null
)