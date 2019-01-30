package com.widyatama.univcare.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.widyatama.core.base.BaseResponse


class UniversityResponse : BaseResponse() {
    class University{
        @SerializedName("web_pages")
        @Expose
        var webPages: List<String>? = null
        @SerializedName("alpha_two_code")
        @Expose
        var alphaTwoCode: String? = null
        @SerializedName("state-province")
        @Expose
        var stateProvince: Any? = null
        @SerializedName("country")
        @Expose
        var country: String? = null
        @SerializedName("domains")
        @Expose
        var domains: List<String>? = null
        @SerializedName("name")
        @Expose
        var name: String? = null
    }

}