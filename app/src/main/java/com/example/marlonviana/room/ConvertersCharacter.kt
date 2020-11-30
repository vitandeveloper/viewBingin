package com.example.marlonviana.room

import androidx.room.TypeConverter
import com.example.marlonviana.model.Comics
import com.example.marlonviana.model.Thumbnail
import com.google.gson.Gson

class ConvertersCharacter {
    @TypeConverter
    fun fromThumbnailString(value: String?): Thumbnail? {
        return value?.let { Gson().fromJson(value,Thumbnail::class.java) }
    }

    @TypeConverter
    fun thumbnailToString(thumbnail: Thumbnail?): String {
        return Gson().toJson(thumbnail)
    }

    @TypeConverter
    fun fromComicsString(value: String?): Comics? {
        return value?.let { Gson().fromJson(value,Comics::class.java) }
    }

    @TypeConverter
    fun comicsToString(comics: Comics): String {
        return Gson().toJson(comics)
    }
}