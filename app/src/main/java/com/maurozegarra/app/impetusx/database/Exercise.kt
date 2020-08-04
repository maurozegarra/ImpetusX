package com.maurozegarra.app.impetusx.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_table")
data class Exercise(

    @PrimaryKey(autoGenerate = true)
    var exerciseId: Long = 0L,

    @ColumnInfo(name = "start_time_milli")
    val startTimeMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "end_time_milli")
    var endTimeMilli: Long = startTimeMilli,

    @ColumnInfo(name = "exercise_title")
    var exerciseTitle: String = "Exercise Title",

    @ColumnInfo(name = "repetition")
    var repetition: Int = -1,

    @ColumnInfo(name = "time_hold_second")
    var timeHoldSecond: Int = -1,

    @ColumnInfo(name = "type")
    var type: Int = -1, // work, rest

    @ColumnInfo(name = "auto_pause")
    var autoPause: Boolean = false,

    @ColumnInfo(name = "timer_second")
    var timerSecond: Int = -1,

    @ColumnInfo(name = "condition")
    var condition: Int = -1 // "off", "don't run", "run only"
)
