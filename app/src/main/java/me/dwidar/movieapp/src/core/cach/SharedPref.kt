package me.dwidar.movieapp.src.core.cach

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun saveTimeToSharedPref(application: Application)
{
    val sharedPreferences: SharedPreferences = application.getSharedPreferences("TIME", Context.MODE_PRIVATE)
    var editor = sharedPreferences.edit()

    var formatter = DateTimeFormatter.ofPattern("dd/M/yyyy hh:mm:ss")
    var formattedDate = LocalDateTime.now().format(formatter)

    editor.putString("time",formattedDate)
    editor.apply()
}

@RequiresApi(Build.VERSION_CODES.O)
fun getTimeFromSharedPref(application: Application) : LocalDateTime
{
    val sharedPreferences: SharedPreferences = application.getSharedPreferences("TIME", Context.MODE_PRIVATE)

    var formatter = DateTimeFormatter.ofPattern("dd/M/yyyy hh:mm:ss")
    var formattedDate = LocalDateTime.now().format(formatter)

    return LocalDateTime.parse(sharedPreferences.getString("time",formattedDate))
}