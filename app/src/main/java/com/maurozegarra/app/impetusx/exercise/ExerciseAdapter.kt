package com.maurozegarra.app.impetusx.exercise

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maurozegarra.app.impetusx.database.Exercise
import com.maurozegarra.app.impetusx.databinding.ListItemExerciseBinding

class ExerciseAdapter(val clickListener: ExerciseListener) :
    ListAdapter<Exercise, ExerciseAdapter.ViewHolder>(ExerciseDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemExerciseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Exercise, clickListener: ExerciseListener) {
            binding.exercise = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemExerciseBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class ExerciseDiffCallback : DiffUtil.ItemCallback<Exercise>() {
        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem.exerciseId == newItem.exerciseId
        }

        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem == newItem
        }
    }
}

class ExerciseListener(val clickListener: (exerciseId: Long) -> Unit) {
    fun onClick(exercise: Exercise) = clickListener(exercise.exerciseId)
}
