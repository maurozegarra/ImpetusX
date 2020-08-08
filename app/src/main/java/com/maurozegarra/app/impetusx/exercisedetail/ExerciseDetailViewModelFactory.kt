package com.maurozegarra.app.impetusx.exercisedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maurozegarra.app.impetusx.database.ExerciseDao

class ExerciseDetailViewModelFactory(
    private val exerciseId: Long,
    private val exerciseDao: ExerciseDao
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseDetailViewModel::class.java)) {
            return ExerciseDetailViewModel(exerciseId, exerciseDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
