package com.maurozegarra.app.impetusx.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExerciseDao {

    @Insert
    fun insert(exercise: Exercise)

    @Update
    fun update(exercise: Exercise)

    @Query("SELECT * FROM exercise_table WHERE exerciseId = :key")
    fun get(key: Long): Exercise

    /*
    @Delete
    fun delete(exercise: Exercise)
    */

    /*
    @Query("DELETE FROM exercise_table")
    fun clear()
    */

    @Query("SELECT * FROM exercise_table ORDER BY exercise_title ASC")
    fun getAll(): LiveData<List<Exercise>>

    @Query("SELECT * FROM exercise_table ORDER BY exerciseId DESC LIMIT 1")
    // the return type `Exercise?` is nullable because in the beginning and if we clear all the
    // content there is no `Exercise`
    fun getLatestExercise(): Exercise?
}
