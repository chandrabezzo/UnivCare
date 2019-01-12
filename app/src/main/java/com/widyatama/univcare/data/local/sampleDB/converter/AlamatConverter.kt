package com.widyatama.coreandroid.data.local.sampleDB.converter

import androidx.room.TypeConverter
import com.widyatama.coreandroid.data.model.Alamat
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