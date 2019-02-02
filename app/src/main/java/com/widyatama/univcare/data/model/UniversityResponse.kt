package com.widyatama.univcare.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.widyatama.core.base.BaseResponse


class UniversityResponse : BaseResponse() {

    @Entity(tableName = "University")
    class University{
        @SerializedName("web_pages")
        @ColumnInfo(name = "web_pages")
        @Expose
        var webPages: List<String>? = null

        @SerializedName("alpha_two_code")
        @ColumnInfo(name = "alpha_two_code")
        @Expose
        var alphaTwoCode: String? = null

        @SerializedName("state-province")
        @ColumnInfo(name = "state-province")
        @Expose
        var stateProvince: String? = null

        @SerializedName("country")
        @ColumnInfo(name = "country")
        @Expose
        var country: String? = null

        @SerializedName("domains")
        @ColumnInfo(name = "domains")
        @Expose
        var domains: List<String>? = null

        @PrimaryKey
        @NonNull
        @SerializedName("name")
        @ColumnInfo(name = "name")
        @Expose
        var name: String? = null
    }

}