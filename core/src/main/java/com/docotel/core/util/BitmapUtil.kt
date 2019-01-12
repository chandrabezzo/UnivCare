package com.docotel.core.util

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.ExifInterface
import android.net.Uri
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Base64
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException


/**
 * Created by docotel on 3/8/16.
 */
object BitmapHelper {

    private val maxHeight = 960.0f
    private val maxWidth = 720.0f

    fun fromDrawable(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }

        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    fun fromString(string: String): Bitmap? {
        try {
            val encodeByte = Base64.decode(string, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    fun makeRounded(context: Context, bitmap:Bitmap, cornerRadius:Float) : RoundedBitmapDrawable {
        val res = context.resources
        val rounded = RoundedBitmapDrawableFactory.create(res, bitmap)
        rounded.cornerRadius = cornerRadius
        return rounded
    }

    @Throws(Exception::class)
    fun createTemporaryFile(part: String, ext: String): File {
        var tempDir = Environment.getExternalStorageDirectory()
        tempDir = File(tempDir.getAbsolutePath() + "/temp/siab/imgs")
        if (!tempDir.exists()) {
            tempDir.mkdirs()
        }
        return File.createTempFile(part, ext, tempDir)
    }

    fun compress(imagePath: String): ByteArray {
        var scaledBitmap: Bitmap? = null

        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        var bmp = BitmapFactory.decodeFile(imagePath, options)

        var actualHeight = options.outHeight
        var actualWidth = options.outWidth

        var imgRatio = actualWidth.toFloat() / actualHeight.toFloat()
        val maxRatio = (maxWidth / maxHeight)

        println("before actual width: $actualWidth | actual heigth $actualHeight")
        println("before img ration: $imgRatio | maxRatio: $maxRatio")

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = (maxHeight / actualHeight)
                actualWidth = (imgRatio * actualWidth).toInt()
                actualHeight = maxHeight.toInt()
            } else if (imgRatio > maxRatio) {
                imgRatio = (maxWidth / actualWidth)
                actualHeight = (imgRatio * actualHeight).toInt()
                actualWidth = maxWidth.toInt()
            } else {
                actualHeight = maxHeight.toInt()
                actualWidth = maxWidth.toInt()
            }
        }

        println("after actual width: $actualWidth | actual heigth $actualHeight")
        println("after img ration: $imgRatio | maxRatio: $maxRatio")

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight)
        options.inJustDecodeBounds = false
        options.inDither = false
        options.inPurgeable = true
        options.inInputShareable = true
        options.inTempStorage = ByteArray(16 * 1024)

        try {
            bmp = BitmapFactory.decodeFile(imagePath, options)
        } catch (exception: OutOfMemoryError) {
            exception.printStackTrace()
        }

        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888)
        } catch (exception: OutOfMemoryError) {
            exception.printStackTrace()
        }

        val ratioX = actualWidth / options.outWidth.toFloat()
        val ratioY = actualHeight / options.outHeight.toFloat()
        val middleX = actualWidth / 2.0f
        val middleY = actualHeight / 2.0f

        val scaleMatrix = Matrix()
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY)

        val canvas = Canvas(scaledBitmap)
        canvas.matrix = scaleMatrix
        canvas.drawBitmap(bmp, middleX - bmp.width / 2, middleY - bmp.height / 2, Paint(Paint.FILTER_BITMAP_FLAG))

        val exif: ExifInterface
        try {
            exif = ExifInterface(imagePath)
            val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0)
            val matrix = Matrix()
            if (orientation == 6) {
                matrix.postRotate(90f)
            } else if (orientation == 3) {
                matrix.postRotate(180f)
            } else if (orientation == 8) {
                matrix.postRotate(270f)
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap!!.width, scaledBitmap.height, matrix, true)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val out = ByteArrayOutputStream()
        scaledBitmap!!.compress(Bitmap.CompressFormat.JPEG, 90, out)
        val byteArray = out.toByteArray()
        println("file size: ${fileSizeInKB(byteArray.size.toLong())}")
        replaceFile(imagePath, byteArray)
        return byteArray
    }

    fun replaceFile(currentPath:String, newBitmap:ByteArray){
        try {
            val newFile = File(currentPath)
            newFile.delete()
            newFile.writeBytes(newBitmap)
            newFile.createNewFile()
        }catch (e:IOException){
            e.printStackTrace()
        }
    }

    fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val heightRatio = Math.round(height.toFloat() / reqHeight.toFloat())
            val widthRatio = Math.round(width.toFloat() / reqWidth.toFloat())
            inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
        }
        val totalPixels = (width * height).toFloat()
        val totalReqPixelsCap = (reqWidth * reqHeight * 2).toFloat()

        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++
        }

        return inSampleSize
    }

    fun getRealPathFromURI_API19(context: Context, uri: Uri): String {
        var filePath = ""
        val wholeID = DocumentsContract.getDocumentId(uri)
        val id = wholeID.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
        val column = arrayOf(MediaStore.Images.Media.DATA)
        val sel = MediaStore.Images.Media._ID + "=?"
        val cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column, sel, arrayOf(id), null)
        val columnIndex = cursor.getColumnIndex(column[0])

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex)
        }
        cursor.close()
        return filePath
    }

    fun fileSizeInKB(byteSize:Long):String{
        return "file size ${byteSize/1024} kb"
    }

    fun dpFromPx(context: Context, px: Float): Float {
        return px / context.resources.displayMetrics.density
    }

    fun pxFromDp(context: Context, dp: Float): Float {
        return dp * context.resources.displayMetrics.density
    }
}

fun Bitmap.toBase64():String{
    val byteArrayOutputStream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    val result = Base64.encodeToString(byteArray, Base64.DEFAULT)
    return result
}
