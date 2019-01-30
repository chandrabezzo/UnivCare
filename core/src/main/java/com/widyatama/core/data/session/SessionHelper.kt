package com.widyatama.core.data.session

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by bezzo on 11/01/18.
 */

class SessionHelper{
    private val PREFS_FILENAME = "com.widyatama.univcare.prefs"
    private val SESSION = "session"
    private val USERNAME = "username"
//    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);
    private lateinit var prefs: SharedPreferences

    var session: String
        get() = prefs.getString(SESSION, null)
        set(value) = prefs.edit().putString(SESSION, value).apply()

    var username: String
        get() = prefs.getString(USERNAME, null)
        set(value) = prefs.edit().putString(USERNAME, value).apply()

    fun deleteAllSession(){
        prefs.edit().clear().commit()
    }

}
