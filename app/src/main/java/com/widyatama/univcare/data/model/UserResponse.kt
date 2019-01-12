package com.widyatama.coreandroid.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.widyatama.core.base.BaseResponse
import com.widyatama.coreandroid.constanta.AppConstans
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponse : BaseResponse() {
    @SerializedName("data")
    @Expose
    var data: ArrayList<User>? = null

    @Entity(tableName = AppConstans.USER)
    class User {
        @PrimaryKey
        @NonNull
        @SerializedName("id")
        @ColumnInfo(name = "id")
        @Expose
        var id : Int? = null

        @SerializedName("nama")
        @ColumnInfo(name = "nama")
        @Expose
        var nama: String? = null

        @SerializedName("jabatan")
        @ColumnInfo(name = "jabatan")
        @Expose
        var jabatan: String? = null

        @SerializedName("alamat")
        @ColumnInfo(name = "alamat")
        @Expose
        var alamat : Alamat? = null
    }
}