package com.maurozegarra.app.impetusx.addexercise

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.maurozegarra.app.impetusx.R
import com.maurozegarra.app.impetusx.database.Exercise
import com.maurozegarra.app.impetusx.database.WorkoutDatabase
import com.maurozegarra.app.impetusx.databinding.FragmentAddExerciseBinding
import com.maurozegarra.app.impetusx.hideKeyboard

class AddExerciseFragment : Fragment() {
    private lateinit var viewModel: AddExerciseViewModel
    private lateinit var binding: FragmentAddExerciseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddExerciseBinding.inflate(inflater, container, false)

        val application = requireNotNull(activity).application
        val dataSource = WorkoutDatabase.getInstance(application).exerciseDao
        val viewModelFactory = AddExerciseViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AddExerciseViewModel::class.java)

        viewModel.navigateToExercise.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findNavController().navigate(AddExerciseFragmentDirections.actionAddExerciseFragmentToExerciseFragment())
                viewModel.doneNavigation()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_exercise -> save()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun save() {
        val exercise = Exercise()
        exercise.exerciseTitle = binding.editExerciseTitle.text.toString()
        viewModel.save(exercise)

        hideKeyboard()
    }
}
