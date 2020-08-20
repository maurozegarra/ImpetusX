package com.maurozegarra.app.impetusx.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_table")
data class Exercise(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exercise_id")
    var exerciseId: Long = 0L,

    @ColumnInfo(name = "exercise_name")
    var exerciseName: String = "Exercise Name",

    var type: String = "", // work, rest

    // later define behavior
    @ColumnInfo(name = "auto_pause")
    var autoPause: Boolean = false,

    // must!
    @ColumnInfo(name = "timer_second")
    var timerSecond: Int = -1,

    // later
    var condition: Int = -1, // "off", "don't run", "run only"

    // must!
    var repetition: Int = -1,

    // must!
    var weight: Int = -1

//    @ColumnInfo(name = "start_time_milli")
//    val startTimeMilli: Long = System.currentTimeMillis(),

//    @ColumnInfo(name = "end_time_milli")
//    var endTimeMilli: Long = startTimeMilli,

//    @ColumnInfo(name = "time_hold_second")
//    var timeHoldSecond: Int = -1,
)
