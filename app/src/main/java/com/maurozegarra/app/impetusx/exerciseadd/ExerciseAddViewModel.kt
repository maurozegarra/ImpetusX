package com.maurozegarra.app.impetusx.exerciseadd

import android.app.Application
import androidx.lifecycle.*
import com.maurozegarra.app.impetusx.R
import com.maurozegarra.app.impetusx.database.Exercise
import com.maurozegarra.app.impetusx.database.ExerciseDao
import kotlinx.coroutines.*

class ExerciseAddViewModel(val database: ExerciseDao, val app: Application) :
    AndroidViewModel(app) {

    private val typeOptions = app.resources.getIntArray(R.array.type_array)

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    /* Navigation [Exercise Add] to [Exercises] */
    private val _navigateToExercise = MutableLiveData<Boolean>()
    val navigateToExercise: LiveData<Boolean>
        get() = _navigateToExercise

    fun save(exercise: Exercise) {
        uiScope.launch {
            exercise.type = typeOptions[typeSelection.value!!]
            insert(exercise)
            _navigateToExercise.value = true
        }
    }

    private suspend fun insert(exercise: Exercise) {
        withContext(Dispatchers.IO) {
            database.insert(exercise)
        }
    }

    fun onExerciseNavigated() {
        _navigateToExercise.value = false
    }

    init {
        _navigateToExercise.value = false
    }

    fun setTypeSelected(type: Int) {
        _typeSelection.value = type
    }

    private val _typeSelection = MutableLiveData<Int>()
    val typeSelection: LiveData<Int>
        get() = _typeSelection

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
