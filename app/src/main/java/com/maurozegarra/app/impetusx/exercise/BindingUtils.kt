package com.maurozegarra.app.impetusx.exercise

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.maurozegarra.app.impetusx.R
import com.maurozegarra.app.impetusx.database.Exercise

@BindingAdapter("exerciseTitle")
fun TextView.setTitle(exercise: Exercise?) {
    exercise?.let {
        text = exercise.exerciseName
    }
}

@BindingAdapter("exerciseType")
fun TextView.setType(exercise: Exercise?) {
    exercise?.let {
        text = context.resources.getString(R.string.exercise_label_type, exercise.type)
    }
}

@BindingAdapter("exerciseTimer")
fun TextView.setTimer(exercise: Exercise?) {
    exercise?.let {
        text = context.resources.getString(
            R.string.exercise_label_timer,
            exercise.timerSecond
        )
    }
}

@BindingAdapter("exerciseRepetition")
fun TextView.setRepetition(exercise: Exercise?) {
    exercise?.let {
        text = context.resources.getString(
            R.string.exercise_label_repetition,
            exercise.repetition
        )
    }
}

@BindingAdapter("exerciseWeight")
fun TextView.setWeight(exercise: Exercise?) {
    exercise?.let {
        text = context.resources.getString(
            R.string.exercise_label_weight,
            exercise.weight
        )
    }
}

@BindingAdapter("app:goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}
