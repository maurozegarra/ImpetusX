package com.maurozegarra.app.impetusx.exerciseadd

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.maurozegarra.app.impetusx.R
import com.maurozegarra.app.impetusx.database.Exercise
import com.maurozegarra.app.impetusx.database.WorkoutDatabase
import com.maurozegarra.app.impetusx.databinding.FragmentExerciseAddBinding
import com.maurozegarra.app.impetusx.hideKeyboard

class ExerciseAddFragment : Fragment() {
    private lateinit var binding: FragmentExerciseAddBinding
    private lateinit var viewModel: ExerciseAddViewModel
    private var selectedType = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseAddBinding.inflate(inflater, container, false)

        val application = requireNotNull(activity).application
        val dataSource = WorkoutDatabase.getInstance(application).exerciseDao
        val viewModelFactory = ExerciseAddViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ExerciseAddViewModel::class.java)

        viewModel.navigateToExercise.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findNavController().navigate(ExerciseAddFragmentDirections.actionAddExerciseFragmentToExerciseFragment())
                viewModel.onExerciseNavigated()
            }
        })

        ArrayAdapter.createFromResource(
            application,
            R.array.exercise_type_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerType.adapter = adapter
        }

        binding.spinnerType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedType = binding.spinnerType.selectedItem.toString().trim()
                //Toast.makeText(context, selectedType, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.pickerRepetition.minValue = 1
        binding.pickerRepetition.maxValue = 100
        binding.pickerWeight.minValue = 0
        binding.pickerWeight.maxValue = 100

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_exercise -> save()
        }
        return true
    }

    private fun save() {
        val exercise = Exercise()

        val exerciseName = binding.editExerciseName.text.toString()

        // todo: where go validations?
        if (TextUtils.isEmpty(exerciseName)) {
            Toast.makeText(context, R.string.empty_name_not_saved, Toast.LENGTH_LONG).show()
            return
        }

        exercise.exerciseName = exerciseName
        exercise.type = selectedType

        exercise.repetition = binding.pickerRepetition.value
        exercise.weight = binding.pickerWeight.value
        // todo: validate!
        exercise.timerSecond = binding.editTimer.text.toString().trim().toInt()

        viewModel.save(exercise)

        hideKeyboard()
    }
}
