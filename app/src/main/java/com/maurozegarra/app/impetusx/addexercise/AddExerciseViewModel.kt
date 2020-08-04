package com.maurozegarra.app.impetusx.addexercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maurozegarra.app.impetusx.database.Exercise
import com.maurozegarra.app.impetusx.database.ExerciseDao
import kotlinx.coroutines.*

class AddExerciseViewModel(val database: ExerciseDao) : ViewModel() {

    private var exercise = MutableLiveData<Exercise>()

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToExercise = MutableLiveData<Boolean>()
    val navigateToExercise: LiveData<Boolean>
        get() = _navigateToExercise

    fun save(exercise: Exercise) {
        uiScope.launch {
            insert(exercise)
            _navigateToExercise.value = true
        }
    }

    private suspend fun insert(exercise: Exercise) {
        withContext(Dispatchers.IO) {
            database.insert(exercise)
        }
    }

    fun doneNavigation() {
        _navigateToExercise.value = false
    }

    init {
        _navigateToExercise.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
