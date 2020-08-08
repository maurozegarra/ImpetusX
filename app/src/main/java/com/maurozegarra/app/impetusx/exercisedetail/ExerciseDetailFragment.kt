package com.maurozegarra.app.impetusx.exercisedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.maurozegarra.app.impetusx.database.WorkoutDatabase
import com.maurozegarra.app.impetusx.databinding.FragmentExerciseDetailBinding

class ExerciseDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentExerciseDetailBinding.inflate(inflater, container, false)

        val app = requireNotNull(activity).application
        val args by navArgs<ExerciseDetailFragmentArgs>()
        val dataSource = WorkoutDatabase.getInstance(app).exerciseDao
        val viewModelFactory = ExerciseDetailViewModelFactory(args.exerciseId, dataSource)
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(ExerciseDetailViewModel::class.java)

        binding.exerciseDetailViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToExercise.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findNavController().navigate(ExerciseDetailFragmentDirections.actionExerciseDetailFragmentToExerciseFragment())
                viewModel.onExerciseNavigated()
            }
        })

        return binding.root
    }
}
