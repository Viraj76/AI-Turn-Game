package com.appsv.turngame.data.local.room.history

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromList(list: List<Int>?): String? {
        return list?.joinToString(separator = ",")
    }

    @TypeConverter
    fun toList(data: String?): List<Int>? {
        return data?.split(",")?.map { it.toInt() }
    }
}
