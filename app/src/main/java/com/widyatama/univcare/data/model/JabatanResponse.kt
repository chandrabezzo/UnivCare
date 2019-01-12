package com.widyatama.coreandroid.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.widyatama.core.base.BaseResponse
import com.widyatama.coreandroid.constanta.AppConstans
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class JabatanResponse : BaseResponse() {
    @SerializedName("data")
    @Expose
    var data: ArrayList<Jabatan>? = null

    @Entity(tableName = AppConstans.JABATAN)
    class Jabatan {
        @PrimaryKey
        @NonNull
        @SerializedName("id")
        @ColumnInfo(name = "id")
        @Expose
        var id: Int? = null

        @SerializedName("jenis")
        @ColumnInfo(name = "jenis")
        @Expose
        var jenis: String? = null
    }
}