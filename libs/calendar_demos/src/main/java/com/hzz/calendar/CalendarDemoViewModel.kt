package com.hzz.calendar

import android.content.ContentValues
import android.content.Context
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.os.Build
import android.provider.CalendarContract
import android.util.Log
import androidx.lifecycle.ViewModel
import com.hzz.calendar.data.CalendarEventInfo
import java.time.Instant
import java.time.ZoneId

class CalendarDemoViewModel : ViewModel() {
    fun addEventToCalendar(context: Context, calendarEventInfo: CalendarEventInfo) {
        val contentResolver = context.contentResolver
        val calendar = Calendar.getInstance()
        calendar.setDateTimeByTimestamp(calendarEventInfo.startTimestamp)
        val eventStartTime = calendar.timeInMillis // Start time in milliseconds
        calendar.add(Calendar.MINUTE, calendarEventInfo.lastTime) // Set end time, 1 hour later
        val eventEndTime = calendar.timeInMillis // End time in milliseconds

        val contentValues = ContentValues().apply {
            put(CalendarContract.Events.DTSTART, eventStartTime) // The start time of the event
            put(CalendarContract.Events.DTEND, eventEndTime) // The end time of the event
            put(
                CalendarContract.Events.TITLE,
                "TODO Event: ${calendarEventInfo.title}"
            ) // Event title
            put(
                CalendarContract.Events.DESCRIPTION,
                "Event Description: ${calendarEventInfo.description}"
            )
            put(
                CalendarContract.Events.CALENDAR_ID,
                getCalendarId(context)
            ) // Choose the calendar to add to
            put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().id) // Timezone
            // Set the recurrence rule (e.g., weekly on Monday)
            put(CalendarContract.Events.RRULE, "FREQ=WEEKLY;BYDAY=MO")
        }
        val uri = contentResolver.insert(CalendarContract.Events.CONTENT_URI, contentValues)
        val eventId = uri?.lastPathSegment?.toLong() ?: -1
        val reminderValues = ContentValues().apply {
            put(CalendarContract.Reminders.EVENT_ID, eventId)
            put(CalendarContract.Reminders.MINUTES, 10) // Reminder time before event
            put(
                CalendarContract.Reminders.METHOD,
                CalendarContract.Reminders.METHOD_ALERT
            ) // Type of reminder (alert)
        }
        contentResolver.insert(CalendarContract.Reminders.CONTENT_URI, reminderValues)
    }

    private fun getCalendarId(context: Context): Long? {
        val contentResolver = context.contentResolver
        val cursor = contentResolver.query(
            CalendarContract.Calendars.CONTENT_URI,
            arrayOf(CalendarContract.Calendars._ID, CalendarContract.Calendars.NAME),
            null, null, null
        )
        var calendarId: Long? = null
        cursor?.let {
            if (it.moveToFirst()) {
                // Retrieve the calendar ID (ID of the first available calendar)
                val columnIdIndex = it.getColumnIndex(CalendarContract.Calendars._ID)
                calendarId = it.getLong(columnIdIndex)

                // Optionally, you can print or use the calendar name
                val calendarNameColumnIndex = it.getColumnIndex(CalendarContract.Calendars.NAME)
                val calendarName = it.getString(calendarNameColumnIndex)
                Log.d("Calendar", "Using calendar: $calendarName with ID: $calendarId")
            }
            it.close()
        }
        return calendarId
    }

    private fun Calendar.setDateTimeByTimestamp(timestamp: Long) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val instant = Instant.ofEpochMilli(timestamp)
            val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()
            val time = instant.atZone(ZoneId.systemDefault()).toLocalTime()

            val year = date.year
            val month = date.monthValue
            val day = date.dayOfMonth
            val hour = time.hour
            val minute = time.minute
            val second = time.second
            Log.d(
                "Calendar",
                "year: $year, month: $month, day: $day, hour: $hour, minute: $minute, second: $second"
            )
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month - 1) // Months are zero-based
            set(Calendar.DAY_OF_MONTH, day)
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, second)
        } else {
            val date = java.util.Date(timestamp)
            val year = date.year + 1900
            val month = date.month
            val day = date.date
            val hour = date.hours
            val minute = date.minutes
            val second = date.seconds
            Log.d(
                "Calendar",
                "year: $year, month: $month, day: $day, hour: $hour, minute: $minute, second: $second"
            )
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month - 1) // Months are zero-based
            set(Calendar.DAY_OF_MONTH, day)
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, second)
        }
    }
}