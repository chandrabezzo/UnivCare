package com.widyatama.univcare.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Country {
    @SerializedName("flag")
    @Expose
    var flag: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
}