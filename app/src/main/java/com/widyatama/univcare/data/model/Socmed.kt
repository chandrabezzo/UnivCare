package com.widyatama.coreandroid.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.widyatama.coreandroid.constanta.AppConstans
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = AppConstans.SOCMED)
class Socmed {
    @SerializedName("linkedin")
    @ColumnInfo(name = "linkedin")
    @Expose
    var linkedin: String? = null

    @PrimaryKey
    @NonNull
    @SerializedName("email")
    @ColumnInfo(name = "email")
    @Expose
    var email: String? = null
}