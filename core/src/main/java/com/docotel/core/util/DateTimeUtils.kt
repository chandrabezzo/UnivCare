package com.docotel.core.util

import android.app.DatePickerDialog
import android.content.Context
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*
import java.util.Locale.getDefault

/**
 * Created by bezzo on 05/10/17.
 */

object DateTimeUtils {

    val MOBILE_DATE_FORMAT = "dd-MM-yyyy"
    val SERVICE_DATE_FORMAT = "yyyy-MM-dd"

    val SECOND_MILLIS = 1000
    val MINUTE_MILLIS = 60 * SECOND_MILLIS
    val HOUR_MILLIS = 60 * MINUTE_MILLIS
    val DAY_MILLIS = 24 * HOUR_MILLIS


    fun year(calendar: Calendar): Int {
        return calendar.get(YEAR)
    }


    fun month(calendar: Calendar): Int {
        return calendar.get(MONTH)
    }


    fun day(calendar: Calendar): Int {
        return calendar.get(DAY_OF_MONTH)
    }

    // Calendar CONVERTER

    val todayInCalendar = Calendar.getInstance()

    fun getLastDateOfMonth() : Int {
        val calendar = Calendar.getInstance()
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    // EPOCH CONVERTER

    fun epochToDate(epoch: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = epoch * 1000
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1
        val year = calendar.get(Calendar.YEAR)

        return (if (day < 10) "0$day" else day).toString() + "/" + (if (month < 10) "0$month" else month) + "/" + year
    }

    fun epochToTime(epoch: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = epoch * 1000
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        return (if (hour < 10) "0$hour" else hour).toString() + ":" + if (minute < 10) "0$minute" else minute
    }

    fun epochToDateTime(epoch: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = epoch * 1000
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1
        val year = calendar.get(Calendar.YEAR)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        return ((if (day < 10) "0$day" else day).toString() + "/" + (if (month < 10) "0$month" else month) + "/" + year + " "
                + (if (hour < 10) "0$hour" else hour) + ":" + if (minute < 10) "0$minute" else minute)
    }

    fun dateToEpoch(date: String): Long {
        val splitDate = CommonUtil.getSplittedString(date, "/")

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(splitDate[0]))
        calendar.set(Calendar.MONTH, Integer.parseInt(splitDate[1]) - 1)
        calendar.set(Calendar.YEAR, Integer.parseInt(splitDate[2]))
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        return calendar.timeInMillis / 1000
    }

    fun dateTimeToEpoch(dateTime: String): Long {
        val splitDateTime = CommonUtil.getSplittedString(dateTime, " ")
        val splitDate = CommonUtil.getSplittedString(splitDateTime[0], "/")
        val splitTime = CommonUtil.getSplittedString(splitDateTime[1], ":")

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(splitDate[0]))
        calendar.set(Calendar.MONTH, Integer.parseInt(splitDate[1]) - 1)
        calendar.set(Calendar.YEAR, Integer.parseInt(splitDate[2]))
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(splitTime[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(splitTime[1]))

        return calendar.timeInMillis / 1000
    }

    fun getDateTimeInCalendar(day: Int?, month: Int?, year: Int?, hour: Int?, minute: Int?): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, day!!)
        calendar.set(Calendar.MONTH, month!! - 1)
        calendar.set(Calendar.YEAR, year!!)
        calendar.set(Calendar.HOUR_OF_DAY, hour!!)
        calendar.set(Calendar.MINUTE, minute!!)

        return calendar
    }

    fun getDateInCalendar(day: Int?, month: Int?, year: Int?): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, day!!)
        calendar.set(Calendar.MONTH, month!! - 1)
        calendar.set(Calendar.YEAR, year!!)

        return calendar
    }

    //format date 28/11/1995
    fun dateToCalendar(date: String): Calendar {
        val splitDate = CommonUtil.getSplittedString(date, "/")
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(splitDate[0]))
        calendar.set(Calendar.MONTH, Integer.parseInt(splitDate[1]) - 1)
        calendar.set(Calendar.YEAR, Integer.parseInt(splitDate[2]))

        return calendar
    }

    //format datetime 28/11/1995 19:00
    fun getDateTimeInCalendar(dateTime: String): Calendar {
        val datetimeSplit = CommonUtil.getSplittedString(dateTime, " ")
        val dateSplit = CommonUtil.getSplittedString(datetimeSplit[0], "/")
        val timeSplit = CommonUtil.getSplittedString(datetimeSplit[1], ":")

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, Integer.parseInt(dateSplit[0]))
        calendar.set(Calendar.MONTH, Integer.parseInt(dateSplit[1]) - 1)
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateSplit[2]))
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeSplit[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeSplit[1]))

        return calendar
    }

    fun getAddedDayCalendar(date: String, addDay: Int): Calendar {
        val calendar = dateToCalendar(date)
        calendar.add(Calendar.DAY_OF_MONTH, addDay)

        return calendar
    }

    fun getAddedMonthCalendar(date: String, addMonth: Int): Calendar {
        val calendar = dateToCalendar(date)
        calendar.add(Calendar.MONTH, addMonth)

        return calendar
    }

    fun getAddedYearCalendar(date: String, addYear: Int): Calendar {
        val calendar = dateToCalendar(date)
        calendar.add(Calendar.YEAR, addYear)

        return calendar
    }

    fun countDate(tglAwal: Long?, tglAkhir: Long?): Long? {
        val tglMulai = epochToDate(tglAwal!!)
        val tglSelesai = epochToDate(tglAkhir!!)

        val startCalendar = dateToCalendar(tglMulai)
        val endCalendar = dateToCalendar(tglSelesai)

        val dateInMilis = endCalendar.timeInMillis - startCalendar.timeInMillis

        return dateInMilis / (24 * 60 * 60 * 1000)
    }

    // END Calendar CONVERTER

    fun getHour(hour: Int? = 0): String {
        var textHour = ""

        textHour = if (hour ?: 0 < 10) {
            "0" + hour!!
        } else {
            hour!!.toString()
        }

        return textHour
    }

    fun getMinute(minute: Int?): String {
        var textMinute = ""

        textMinute = if (minute ?: 0 < 10) {
            "0" + minute!!
        } else {
            minute!!.toString()
        }

        return textMinute
    }

    internal fun timestamp(): Long {
        return System.currentTimeMillis() / 1000
    }

    /**
     * @see BaseApplication,
     * if Locale by default is Locale("in"),
     * dateFormat will be fail parsed if dateStr locale format is 'en'
     */
    fun dateFormat(format: String): SimpleDateFormat {
        return SimpleDateFormat(format, getDefault())
    }

    fun dateStr(from: Date): String {
        val formatter = dateFormat(MOBILE_DATE_FORMAT)
        var result = formatter.format(from)
        return result
    }

    fun dateStr(from: Date, customFormat: String): String {
        val formatter = dateFormat(customFormat)
        var result = formatter.format(from)
        return result
    }

    fun dateObj(from: String): Date {
        val formatter = dateFormat(MOBILE_DATE_FORMAT)
        val result = formatter.parse(from)
        return result
    }

    fun dateObj(from: String, customFormat: String): Date {
        val formatter = dateFormat(customFormat)
        val result = formatter.parse(from)
        return result
    }

    fun changeFormat(stringDate: String, prevFormat: String, toFormat: String): String {
        val df_prevFormat = dateFormat(prevFormat)
        val df_toFormat = dateFormat(toFormat)
        val date: Date
        try {
            date = df_prevFormat.parse(stringDate)
        } catch (e: ParseException) {
            return ""
        }

        return df_toFormat.format(date)
    }

    fun datePicker(context: Context, listener: DatePickerDialogListener, defaultDate: Date, requestCode: Int, requestedFormat: String) {
        val cal_defaultDate = getInstance()
        cal_defaultDate.time = defaultDate
        DatePickerDialog(context, DatePickerDialog.OnDateSetListener { datePicker, i, i1, i2 ->
            listener.onDateSet(requestCode, dateFormat(requestedFormat)
                    .format(GregorianCalendar(datePicker.year, datePicker.month, datePicker.dayOfMonth).time))
        }
                , year(cal_defaultDate), month(cal_defaultDate), day(cal_defaultDate)).show()
    }

    fun today(): Date {
        return Calendar.getInstance().time
    }

    fun todayStr(format: String): String {
        var dateStr = dateStr(today(), format)
        return dateStr
    }

    interface DatePickerDialogListener {
        fun onDateSet(requestCode: Int, date: String)
    }

    fun getTotalDayBetween(date1: Date, date2: Date): Int {
        val c1 = getInstance()
        c1.time = date2

        val c2 = getInstance()
        c2.time = date1

        return c1.get(Calendar.DAY_OF_YEAR) - c2.get(Calendar.DAY_OF_YEAR)
    }

    fun getTimeAgo(time: Long): String? {
        var time = time
        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000
        }

        val now = System.currentTimeMillis()
        if (time > now || time <= 0) {
            return null
        }

        val diff = now - time
        return if (diff < MINUTE_MILLIS) {
            "baru saja"
        } else if (diff < 2 * MINUTE_MILLIS) {
            "1 menit lalu"
        } else if (diff < 50 * MINUTE_MILLIS) {
            "${diff / MINUTE_MILLIS} menit lalu"
        } else if (diff < 90 * MINUTE_MILLIS) {
            "sejam lalu"
        } else if (diff < 24 * HOUR_MILLIS) {
            "${diff / HOUR_MILLIS} jam lalu"
        } else if (diff < 48 * HOUR_MILLIS) {
            "kemarin"
        } else {
            "${diff / DAY_MILLIS} hari lalu"
        }
    }
}
