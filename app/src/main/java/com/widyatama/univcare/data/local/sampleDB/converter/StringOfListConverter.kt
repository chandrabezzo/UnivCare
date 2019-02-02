package com.widyatama.univcare.data.local.sampleDB.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringOfListConverter {
    @TypeConverter
    fun jsonToList(value: String): List<String> {
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson<List<String>>(value, listType)
    }

    @TypeConverter
    fun listToJson(list: List<String>): String {
        val gson = Gson()

        return gson.toJson(list)
    }
}