package com.docotel.core.security

import android.util.Base64
import java.nio.ByteBuffer
import java.nio.CharBuffer
import java.nio.charset.StandardCharsets

/**
 * Created by Lafran on 11/22/17.
 */
class Cryptonite {

    external fun hiddenKey(): String

    companion object {

        init {
            System.loadLibrary("hello-jni")
        }

        const val secretMessage = "*this message in hidden*"
        const val expiredMessage = "expired"
        const val TIME_EXPIRED: Long = 3600 * 1

        private var instance: Cryptonite? = null

        fun singleton(): Cryptonite {
            if (instance == null) {
                instance = Cryptonite()
            }
            return instance!!
        }

        fun obfuscate(source: String): String {
            val crypt = Cryptonite.singleton()
            val secretKey = crypt.hiddenKey()
            return crypt.hashData(source, secretKey)
        }

        fun deobfuscate(source: String): String {
            val crypt = Cryptonite.singleton()
            val secretKey = crypt.hiddenKey()
            return crypt.parseData(source, secretKey)
        }
    }


    private fun getTime(): String {
        val timeSince1970Str = (System.currentTimeMillis() / 1000).toString()
        val trimmedTime = timeSince1970Str
        return trimmedTime
    }

    private fun hashData(data: String, cid: String): String {
        val revTime = getTime().reversed()
        val composed = revTime + "|%" + data
        val encrypted = doubleEncrypt(composed, cid)
        return encrypted
    }

    private fun parseData(data: String, cid: String): String {
        var result = Cryptonite.secretMessage
        val parsedString = doubleDecrypt(data, cid)
        val strArray = parsedString.split("|%")
        if (strArray.size >= 2) {
            val strRevTime = strArray.first().reversed()
            val time = strRevTime.toLong()
            if (tsDiff(time)) {
                result = strArray.last()
            } else {
                result = Cryptonite.expiredMessage
            }
        }
        return result
    }

    fun encrypt(str: ByteArray, key: String): ByteArray {
        val buffer = ByteArray(str.size)
        for (i in 0..str.size - 1) {
            val chr8: Int = str[i].toInt()
            val charIndex = (i + key.length - 1) % key.length
            val keyChar = key[charIndex].toInt()
            val resChar = (chr8 + keyChar) % 128

            buffer[i] = resChar.toByte()
        }
        return buffer
    }

    private fun decrypt(str: ByteArray, key: String): ByteArray {
        val buffer = ByteArray(str.size)
        for (i in 0..str.size - 1) {
            val chr = str[i].toInt()
            val charIndex = (i + key.length - 1) % key.length
            val keyChar = key[charIndex].toInt()
            val resChar = (chr - keyChar + 128) % 128
            buffer[i] = resChar.toByte()
        }
        return buffer
    }

    private fun doubleEncrypt(str: String, cid: String): String {
        var chars = str.toCharArray()
        var byteBuffer = chars.toASCIICharBuffer()
        var nonZeroBytes = ByteArray(byteBuffer.remaining())
        byteBuffer.get(nonZeroBytes)

        var result1 = encrypt(nonZeroBytes, cid)
        val base64Data = Base64.encode(result1, Base64.NO_WRAP)
        val resultStr = String(base64Data, StandardCharsets.UTF_8)
        var rTrim = resultStr.replace("=", "")
        rTrim = rTrim.replace("+", "^").replace("/", "-")
        return rTrim
    }

    fun doubleDecrypt(str: String, cid: String): String {
        val multiplier = 4.0
        val ceils = Math.ceil(str.length / multiplier) * multiplier
        var strData = str
        while (strData.length < ceils) {
            strData += "="
        }
        strData = strData.replace("^", "+").replace("-", "/")
        val resultData = Base64.decode(strData, Base64.NO_WRAP)
        val resultString = String(resultData, StandardCharsets.UTF_8)
        var byteArray = mutableListOf<Byte>()
        for (i in 0..resultString.length - 1) {
            val byte = resultString[i].toInt().toByte()
            byteArray.add(byte)
        }
        var result1 = decrypt(byteArray.toByteArray(), cid)
        return String(result1, StandardCharsets.UTF_8)
    }

    private fun tsDiff(ts: Long): Boolean {
        val current = getTime().toLong()
        val diff = Math.abs(ts - current)
        return diff <= TIME_EXPIRED
    }
}

fun CharArray.toASCIICharBuffer(): ByteBuffer {
    return StandardCharsets.UTF_8.encode(CharBuffer.wrap(this))
}