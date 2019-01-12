package com.widyatama.univcare.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Alamat {
    @SerializedName("rt")
    @Expose
    var rt: String? = null
    @SerializedName("rw")
    @Expose
    var rw: String? = null
    @SerializedName("lokasi")
    @Expose
    var lokasi: String? = null
    @SerializedName("kecamatan")
    @Expose
    var kecamatan: String? = null
    @SerializedName("kabupaten")
    @Expose
    var kabupaten: String? = null
}