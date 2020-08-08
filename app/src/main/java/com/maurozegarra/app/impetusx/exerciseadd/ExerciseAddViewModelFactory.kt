package com.maurozegarra.app.impetusx.exerciseadd

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maurozegarra.app.impetusx.database.ExerciseDao

class ExerciseAddViewModelFactory(
    private val dataSource: ExerciseDao, private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ExerciseAddViewModel::class.java))
            return ExerciseAddViewModel(dataSource, application) as T

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
