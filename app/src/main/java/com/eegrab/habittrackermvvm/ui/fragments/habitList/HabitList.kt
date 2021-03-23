package com.eegrab.habittrackermvvm.ui.fragments.habitList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.eegrab.habittrackermvvm.R
import com.eegrab.habittrackermvvm.databinding.FragmentHabitListBinding


class HabitList : Fragment(R.layout.fragment_habit_list) {

    private lateinit var binding: FragmentHabitListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHabitListBinding.bind(view)

        binding.fabAdd.setOnClickListener {
          findNavController().navigate(R.id.action_habitList_to_createHabitItem)
        }
    }
}