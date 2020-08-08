package com.maurozegarra.app.impetusx.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.maurozegarra.app.impetusx.R
import com.maurozegarra.app.impetusx.database.WorkoutDatabase
import com.maurozegarra.app.impetusx.databinding.FragmentExerciseBinding

class ExerciseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentExerciseBinding.inflate(inflater, container, false)

        val application = requireNotNull(activity).application
        val dataSource = WorkoutDatabase.getInstance(application).exerciseDao
        val viewModelFactory = ExerciseViewModelFactory(dataSource, application)
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(ExerciseViewModel::class.java)

        val adapter = ExerciseAdapter(ExerciseListener { exerciseId ->
            // Toast.makeText(context, "$exerciseId", Toast.LENGTH_SHORT).show()
            viewModel.onExerciseClicked(exerciseId)
        })

        binding.recyclerExercise.adapter = adapter

        viewModel.exercises.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToAddExercise.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findNavController().navigate(ExerciseFragmentDirections.actionExerciseFragmentToAddExerciseFragment())
                viewModel.onExerciseAddNavigated()
            }
        })

        viewModel.navigateToExerciseDetail.observe(viewLifecycleOwner, Observer { exercise ->
            exercise?.let {
                findNavController().navigate(ExerciseFragmentDirections.actionExerciseFragmentToExerciseDetailFragment(exercise))
                viewModel.onExerciseDetailNavigated()
            }
        })

        binding.exerciseViewModel = viewModel
        binding.lifecycleOwner = this

        binding.recyclerExercise.layoutManager = LinearLayoutManager(activity)

        setLabelFragment()

        return binding.root
    }

    private fun setLabelFragment() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.exercise_label)
    }
}
