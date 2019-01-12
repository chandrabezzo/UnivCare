package com.widyatama.univcare.data.network.repository

import com.widyatama.core.data.network.RestApi
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.univcare.data.model.JabatanResponse
import com.widyatama.univcare.data.model.Karyawan
import com.widyatama.univcare.data.model.UserResponse
import com.widyatama.univcare.data.network.ApiEndPoint
import com.rx2androidnetworking.Rx2ANRequest
import io.reactivex.Observable

class CompanyRepo constructor(val schedulerProvider: SchedulerProviderUtil) {

    fun getUser(): Observable<UserResponse> {
        return RestApi.get(ApiEndPoint.USER, null, null, null)
                .getObjectObservable(UserResponse::class.java)
                .compose(schedulerProvider.ioToMainObservableScheduler())
    }

    fun getJabatan(): Observable<JabatanResponse> {
        return RestApi.get(ApiEndPoint.JABATAN, null, null, null)
                .getObjectObservable(JabatanResponse::class.java)
                .compose(schedulerProvider.ioToMainObservableScheduler())
    }

    fun getKaryawan(page : String, limit : String): Rx2ANRequest {
        var params = HashMap<String, String>()
        params["_page"] = page
        params["_limit"] = limit

        return RestApi.get(ApiEndPoint.KARYAWAN, params, null, null)
    }

    fun addKaryawan(value : Karyawan): Rx2ANRequest {
        return RestApi.post(ApiEndPoint.KARYAWAN, null, null, null, value)
    }

    fun getSocmed(): Rx2ANRequest {
        return RestApi.get(ApiEndPoint.SOCMED, null, null, null)
    }

    fun test(): Rx2ANRequest {
        return RestApi.get("http://202.138.242.21:8042/v1/auth/test-mobile", null, null, null)
    }
}