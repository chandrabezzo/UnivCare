package com.docotel.coreandroid.data.network;

import com.docotel.coreandroid.BuildConfig;

/**
 * Created by bezzo on 25/09/17.
 */

public final class ApiEndPoint {
    public static final String USER = BuildConfig.BASE_URL + "user";
    public static final String JABATAN = BuildConfig.BASE_URL + "jabatan";
    public static final String KARYAWAN = BuildConfig.BASE_URL + "karyawan";
    public static final String SOCMED = BuildConfig.BASE_URL + "socialMedia";

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }
}
