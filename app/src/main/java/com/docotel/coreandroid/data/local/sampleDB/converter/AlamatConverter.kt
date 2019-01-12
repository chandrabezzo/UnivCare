package com.docotel.coreandroid.data.local.sampleDB.converter

import androidx.room.TypeConverter
import com.docotel.coreandroid.data.model.Alamat
import com.google.gson.Gson

class AlamatConverter {
    @TypeConverter
    fun jsonToObject(value : String) : Alamat {
        return Gson().fromJson<Alamat>(value, Alamat::class.java)
    }

    @TypeConverter
    fun objectToJson(value : Alamat) : String {
        return Gson().toJson(value)
    }
}