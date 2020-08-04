package com.maurozegarra.app.impetusx.addexercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maurozegarra.app.impetusx.database.ExerciseDao

class AddExerciseViewModelFactory(
    private val dataSource: ExerciseDao
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AddExerciseViewModel::class.java))
            return AddExerciseViewModel(dataSource) as T

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
