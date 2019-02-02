package com.widyatama.univcare.data.network;

import com.widyatama.univcare.BuildConfig;

/**
 * Created by bezzo on 25/09/17.
 */

public final class ApiEndPoint {
    public static final String USER = BuildConfig.BASE_URL + "user";
    public static final String JABATAN = BuildConfig.BASE_URL + "jabatan";
    public static final String KARYAWAN = BuildConfig.BASE_URL + "karyawan";
    public static final String SOCMED = BuildConfig.BASE_URL + "socialMedia";
    public static final String COUNTRIES = BuildConfig.BASE_COUNTRY + "all";
    public static final String COUNTRY = BuildConfig.BASE_COUNTRY + "name/{keyword}";
    public static final String UNIV = BuildConfig.BASE_URL + "search";

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }
}
