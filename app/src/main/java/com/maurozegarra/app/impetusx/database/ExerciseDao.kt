package com.maurozegarra.app.impetusx.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExerciseDao {

    @Insert
    fun insert(exercise: Exercise)

    @Update
    fun update(exercise: Exercise)

    @Query("SELECT * FROM exercise_table WHERE exercise_id = :key")
    fun get(key: Long): Exercise

    @Query("SELECT * FROM exercise_table ORDER BY exercise_name ASC")
    fun getAll(): LiveData<List<Exercise>>

    // the return type `Exercise?` is nullable because in the beginning and if we clear all the
    // content there is no `Exercise`
    @Query("SELECT * FROM exercise_table ORDER BY exercise_id DESC LIMIT 1")
    fun getLatestExercise(): Exercise?

    @Query("SELECT * from exercise_table WHERE exercise_id = :key")
    fun getExerciseWithId(key: Long): LiveData<Exercise>
}
