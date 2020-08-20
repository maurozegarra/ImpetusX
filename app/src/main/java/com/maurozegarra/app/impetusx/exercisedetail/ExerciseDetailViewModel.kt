package com.maurozegarra.app.impetusx.exercisedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maurozegarra.app.impetusx.database.Exercise
import com.maurozegarra.app.impetusx.database.ExerciseDao

class ExerciseDetailViewModel(exerciseId: Long, exerciseDao: ExerciseDao) :
    ViewModel() {

    val exercise = MediatorLiveData<Exercise>()

    init {
        exercise.addSource(exerciseDao.getExerciseWithId(exerciseId), exercise::setValue)
    }

    /* Navigation [Exercise Detail] to [Exercises] */
    private val _navigateToExercise = MutableLiveData<Boolean?>()
    val navigateToExercise: LiveData<Boolean?>
        get() = _navigateToExercise

    fun onClose() {
        _navigateToExercise.value = true
    }

    fun onExerciseNavigated() {
        _navigateToExercise.value = null
    }
}
