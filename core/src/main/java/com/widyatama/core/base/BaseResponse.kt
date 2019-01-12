package com.widyatama.core.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("meta")
    @Expose
    var meta : Meta? = null
    @SerializedName("status")
    @Expose
    var code: Int? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("error")
    @Expose
    var error : Error? = null

    data class Meta(var totalData : Int?, var totalPages : Int?)
    data class Error(var title : String, var detail : String, var code : String)
}