package com.eegrab.habittrackermvvm.ui.fragments.habitList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.eegrab.habittrackermvvm.R
import kotlinx.android.synthetic.main.fragment_habit_list.*


class HabitList : Fragment(R.layout.fragment_habit_list) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        fab_add.setOnClickListener {
          findNavController().navigate(R.id.action_habitList_to_createHabitItem)
        }
    }
}