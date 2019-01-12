package com.docotel.core.util

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.provider.Settings

import com.docotel.core.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import androidx.appcompat.app.AlertDialog

/**
 * Created by bezzo on 22/12/17.
 */

object GpsUtil {

    fun checkStatusGPS(activity: Activity) {
        val mFusedLocation = LocationServices.getFusedLocationProviderClient(activity)
        val manager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            val builder = AlertDialog.Builder(activity)
            builder.setMessage(activity.getString(R.string.turn_on_gps))
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialogInterface, i ->
                        dialogInterface.dismiss()
                        val intentLocationSetting = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                        activity.startActivity(intentLocationSetting)
                    }
                    .setNegativeButton("No") { dialogInterface, i ->
                        dialogInterface.dismiss()
                        activity.finish()
                    }
            val alertDialog = builder.create()

            if (!alertDialog.isShowing) {
                alertDialog.show()
            }
        }
    }

    fun isActive(activity: Activity): Boolean {
        val manager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            false
        } else {
            true
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    fun isMockLocationOn(location: Location): Boolean {
        return location.isFromMockProvider
    }

    fun gotoLocationSetting(context: Context) {
        val builder = AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.pengaturan_gps))
                .setMessage(context.getString(R.string.akurasi_tinggi_mode))
                .setPositiveButton(android.R.string.yes) { dialog, which ->
                    dialog.dismiss()
                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    context.startActivity(intent)
                }
                .setNegativeButton(android.R.string.no) { dialog, which ->
                    dialog.dismiss()
                    System.exit(0)
                }

        val alertDialog = builder.create()
        if (!alertDialog.isShowing) {
            alertDialog.show()
        }
    }
}
