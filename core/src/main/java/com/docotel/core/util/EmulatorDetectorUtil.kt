package com.docotel.core.util

import android.os.Build
import android.os.Environment

import java.io.File

/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright (C) 2013, Vladislav Gingo Skoumal (http://www.skoumal.net)
 *
 */

object EmulatorDetectorUtil {

    private val TAG = "EmulatorDetectorUtil"

    private var rating = -1

    /**
     * Detects if app is currenly running on emulator, or real device.
     * @return true for emulator, false for real devices
     */
    val isEmulator: Boolean
        get() {
            var newRating = 0
            if (rating < 0) {
                if (Build.PRODUCT.contains("sdk") ||
                        Build.PRODUCT.contains("Andy") ||
                        Build.PRODUCT.contains("ttVM_Hdragon") ||
                        Build.PRODUCT.contains("google_sdk") ||
                        Build.PRODUCT.contains("Droid4X") ||
                        Build.PRODUCT.contains("nox") ||
                        Build.PRODUCT.contains("sdk_x86") ||
                        Build.PRODUCT.contains("sdk_google") ||
                        Build.PRODUCT.contains("vbox86p")) {
                    newRating++
                }

                if (Build.MANUFACTURER == "unknown" ||
                        Build.MANUFACTURER == "Genymotion" ||
                        Build.MANUFACTURER.contains("Andy") ||
                        Build.MANUFACTURER.contains("MIT") ||
                        Build.MANUFACTURER.contains("nox") ||
                        Build.MANUFACTURER.contains("TiantianVM")) {
                    newRating++
                }

                if (Build.BRAND == "generic" ||
                        Build.BRAND == "generic_x86" ||
                        Build.BRAND == "TTVM" ||
                        Build.BRAND.contains("Andy")) {
                    newRating++
                }

                if (Build.DEVICE.contains("generic") ||
                        Build.DEVICE.contains("generic_x86") ||
                        Build.DEVICE.contains("Andy") ||
                        Build.DEVICE.contains("ttVM_Hdragon") ||
                        Build.DEVICE.contains("Droid4X") ||
                        Build.DEVICE.contains("nox") ||
                        Build.DEVICE.contains("generic_x86_64") ||
                        Build.DEVICE.contains("vbox86p")) {
                    newRating++
                }

                if (Build.MODEL == "sdk" ||
                        Build.MODEL == "google_sdk" ||
                        Build.MODEL.contains("Droid4X") ||
                        Build.MODEL.contains("TiantianVM") ||
                        Build.MODEL.contains("Andy") ||
                        Build.MODEL == "Android SDK built for x86_64" ||
                        Build.MODEL == "Android SDK built for x86") {
                    newRating++
                }

                if (Build.HARDWARE == "goldfish" ||
                        Build.HARDWARE == "vbox86" ||
                        Build.HARDWARE.contains("nox") ||
                        Build.HARDWARE.contains("ttVM_x86")) {
                    newRating++
                }

                if (Build.FINGERPRINT.contains("generic/sdk/generic") ||
                        Build.FINGERPRINT.contains("generic_x86/sdk_x86/generic_x86") ||
                        Build.FINGERPRINT.contains("Andy") ||
                        Build.FINGERPRINT.contains("ttVM_Hdragon") ||
                        Build.FINGERPRINT.contains("generic_x86_64") ||
                        Build.FINGERPRINT.contains("generic/google_sdk/generic") ||
                        Build.FINGERPRINT.contains("vbox86p") ||
                        Build.FINGERPRINT.contains("generic/vbox86p/vbox86p")) {
                    newRating++
                }

                try {
                    val opengl = android.opengl.GLES20.glGetString(android.opengl.GLES20.GL_RENDERER)
                    if (opengl != null) {
                        if (opengl.contains("Bluestacks") || opengl.contains("Translator"))
                            newRating += 10
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                try {
                    val sharedFolder = File(Environment
                            .getExternalStorageDirectory().toString()
                            + File.separatorChar
                            + "windows"
                            + File.separatorChar
                            + "BstSharedFolder")

                    if (sharedFolder.exists()) {
                        newRating += 10
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                rating = newRating
            }
            return rating > 3
        }

    /**
     * Returns string with human-readable listing of Build.* parameters used in [.isEmulator] method.
     * @return all involved Build.* parameters and its values
     */
    val deviceListing: String
        get() = "Build.PRODUCT: " + Build.PRODUCT + "\n" +
                "Build.MANUFACTURER: " + Build.MANUFACTURER + "\n" +
                "Build.BRAND: " + Build.BRAND + "\n" +
                "Build.DEVICE: " + Build.DEVICE + "\n" +
                "Build.MODEL: " + Build.MODEL + "\n" +
                "Build.HARDWARE: " + Build.HARDWARE + "\n" +
                "Build.FINGERPRINT: " + Build.FINGERPRINT + "\n" +
                "Build.TAGS: " + Build.TAGS + "\n" +
                "GL_RENDERER: " + android.opengl.GLES20.glGetString(android.opengl.GLES20.GL_RENDERER) + "\n" +
                "GL_VENDOR: " + android.opengl.GLES20.glGetString(android.opengl.GLES20.GL_VENDOR) + "\n" +
                "GL_VERSION: " + android.opengl.GLES20.glGetString(android.opengl.GLES20.GL_VERSION) + "\n" +
                "GL_EXTENSIONS: " + android.opengl.GLES20.glGetString(android.opengl.GLES20.GL_EXTENSIONS) + "\n"

    /**
     * Prints all Build.* parameters used in [.isEmulator] method to logcat.
     */
    fun logcat() {
        AppLoggerUtil.i(deviceListing, TAG)
    }

}
