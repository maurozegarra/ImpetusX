package com.maurozegarra.app.impetusx.exercise

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.maurozegarra.app.impetusx.database.Exercise

@BindingAdapter("exerciseTitle")
fun TextView.setTitle(exercise: Exercise?) {
    exercise?.let {
        text = exercise.exerciseTitle
    }
}

@BindingAdapter("app:goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}
