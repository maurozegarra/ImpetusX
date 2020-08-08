package com.maurozegarra.app.impetusx.exercise

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.maurozegarra.app.impetusx.database.Exercise
import com.maurozegarra.app.impetusx.database.ExerciseDao
import kotlinx.coroutines.Job

class ExerciseViewModel(val database: ExerciseDao, application: Application) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()

    val exercises = database.getAll()

    /* Navigation [Exercise] to [Exercise Add] */
    private val _navigateToAddExercise = MutableLiveData<Boolean>()
    val navigateToAddExercise: LiveData<Boolean>
        get() = _navigateToAddExercise

    fun onExerciseAddClicked() {
        _navigateToAddExercise.value = true
    }

    fun onExerciseAddNavigated() {
        _navigateToAddExercise.value = false
    }

    /* Navigation [Exercise] to [Exercise Detail] */
    private val _navigateToExerciseDetail = MutableLiveData<Long>()
    val navigateToExerciseDetail: LiveData<Long>
        get() = _navigateToExerciseDetail

    fun onExerciseClicked(id: Long) {
        _navigateToExerciseDetail.value = id
    }

    fun onExerciseDetailNavigated() {
        _navigateToExerciseDetail.value = null
    }

    init {
        _navigateToAddExercise.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
