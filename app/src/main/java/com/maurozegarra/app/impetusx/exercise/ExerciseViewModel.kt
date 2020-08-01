package com.maurozegarra.app.impetusx.exercise

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.maurozegarra.app.impetusx.database.Exercise
import com.maurozegarra.app.impetusx.database.ExerciseDao
import com.maurozegarra.app.impetusx.formatExercises
import kotlinx.coroutines.*

class ExerciseViewModel(val database: ExerciseDao, application: Application) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var latestExercise = MutableLiveData<Exercise?>()

    private val exercises = database.getAll()
    val exercisesString = Transformations.map(exercises) { exercises ->
        formatExercises(exercises, application.resources)
    }

    private val _navigateToAddExercise = MutableLiveData<Boolean>()
    val navigateToAddExercise: LiveData<Boolean>
        get() = _navigateToAddExercise

    fun onAddExercise() {
        _navigateToAddExercise.value = true
    }

    fun doneNavigation() {
        _navigateToAddExercise.value = false
    }

    init {
        _navigateToAddExercise.value = false
        initializeExercise()
    }

    private fun initializeExercise() {
        uiScope.launch {
            latestExercise.value = getLatestExerciseFromDatabase()
        }
    }

    fun onAdd() {
        uiScope.launch {
            val newExercise = Exercise()
            insert(newExercise)
            latestExercise.value = getLatestExerciseFromDatabase()
        }
    }

    private suspend fun insert(exercise: Exercise) {
        withContext(Dispatchers.IO) {
            database.insert(exercise)
        }
    }

    private suspend fun getLatestExerciseFromDatabase(): Exercise? {
        return withContext(Dispatchers.IO) {
            var exercise = database.getLatestExercise()

            if (exercise == null)
                exercise = null

            exercise
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
