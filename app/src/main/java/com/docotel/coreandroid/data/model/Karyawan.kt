package com.docotel.coreandroid.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.docotel.coreandroid.constanta.AppConstans
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = AppConstans.KARYAWAN)
class Karyawan {
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @Expose
    var id: Int? = null

    @SerializedName("nama")
    @ColumnInfo(name = "nama")
    @Expose
    var nama: String? = null
}