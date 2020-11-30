package com.example.marlonviana.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_character")
data class DataCharacters (
        @PrimaryKey
        @SerializedName("id")
        @Expose
        var id: Int? = null,

        @SerializedName("name")
        @Expose
        var name: String? = null,

        @SerializedName("description")
        @Expose
        var description: String? = null,

        @SerializedName("modified")
        @Expose
        var modified: String? = null,

        @SerializedName("thumbnail")
        @Expose
        var thumbnail: Thumbnail? = null,

        @SerializedName("resourceURI")
        @Expose
        var resourceURI: String? = null,

        @SerializedName("comics")
        @Expose
        var comics: Comics? = null,

        @SerializedName("series")
        @Expose
        var series: Comics? = null
)