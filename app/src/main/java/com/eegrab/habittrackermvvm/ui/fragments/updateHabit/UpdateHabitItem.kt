package com.eegrab.habittrackermvvm.ui.fragments.updateHabit

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.eegrab.habittrackermvvm.R
import com.eegrab.habittrackermvvm.data.models.Habit
import com.eegrab.habittrackermvvm.ui.viewModels.HabitViewModel
import com.eegrab.habittrackermvvm.utils.Calculations
import kotlinx.android.synthetic.main.fragment_create_habit_item.*
import kotlinx.android.synthetic.main.fragment_update_habit_item.*
import java.util.*


class UpdateHabitItem : Fragment(R.layout.fragment_update_habit_item),
        TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{


    val TAG = "Main"


    private var title = ""
    private var description = ""
    private var drawableSelected = 0
    private var timeStamp = ""

    private var day = 0
    private var month = 0
    private var year = 0
    private var hour = 0
    private var minute = 0

    private var cleanDate = ""
    private var cleanTime = ""

    private lateinit var habitViewModel: HabitViewModel
    private val args by navArgs<UpdateHabitItemArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        habitViewModel = ViewModelProvider(this).get(HabitViewModel::class.java)

        et_habitTitle_update.setText(args.selectedHabit.habit_title)
        et_habitDescription_update.setText(args.selectedHabit.habit_description)

        drawableSelected()

        pickDateAndTime()

        btn_confirm_update.setOnClickListener {
            updateHabit()
        }

        setHasOptionsMenu(true)
    }

    private fun updateHabit() {
        title = et_habitTitle_update.text.toString()
        description = et_habitDescription_update.text.toString()

        timeStamp = "$cleanDate $cleanTime"

        if (!(title.isEmpty() || description.isEmpty() || timeStamp.isEmpty() || drawableSelected == 0)) {
            val habit = Habit(args.selectedHabit.id, title, description, timeStamp, drawableSelected)

            habitViewModel.updateHabit(habit)
            Toast.makeText(context, "Habit updated successfully!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateHabitItem_to_habitList)
        } else {
            Toast.makeText(context, "Please fill all the fields!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun drawableSelected() {
        iv_fastFoodSelected_update.setOnClickListener {
            iv_fastFoodSelected_update.isSelected = !iv_fastFoodSelected_update.isSelected
            drawableSelected = R.drawable.ic_fastfood

            iv_smokingSelected_update.isSelected = false
            iv_teaSelected_update.isSelected = false
        }

        iv_smokingSelected_update.setOnClickListener {
            iv_smokingSelected_update.isSelected = !iv_smokingSelected_update.isSelected
            drawableSelected = R.drawable.ic_smoke

            iv_teaSelected_update.isSelected = false
            iv_fastFoodSelected_update.isSelected = false
        }

        iv_teaSelected_update.setOnClickListener{
            iv_teaSelected_update.isSelected = !iv_teaSelected_update.isSelected
            drawableSelected = R.drawable.ic_tea

            iv_fastFoodSelected_update.isSelected = false
            iv_smokingSelected_update.isSelected = false
        }
    }

    private fun pickDateAndTime() {
        btn_pickDate_update.setOnClickListener {
            getDateCalender()
            DatePickerDialog(requireContext(),this,year,month,day).show()
        }

        btn_pickTime_update.setOnClickListener {
            getTimeCalender()
            TimePickerDialog(context,this, hour, minute, true).show()
        }
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        cleanTime = Calculations.cleanTime(hourOfDay,minute)
        tv_timeSelected_update.text = "Time: $cleanTime"
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        cleanDate = Calculations.cleanDate(dayOfMonth,month,year)
        tv_dateSelected_update.text = "Date: $cleanDate"
    }

    private fun getTimeCalender(){
        val cal = Calendar.getInstance()
        hour = cal.get(Calendar.HOUR_OF_DAY)
        minute = cal.get(Calendar.MINUTE)
    }

    private fun getDateCalender(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.single_item_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_delete -> deleteHabit(args.selectedHabit)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteHabit(habit:Habit) {
        habitViewModel.deleteHabit(habit)
        Toast.makeText(context, "Habit successfully deleted!", Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.action_updateHabitItem_to_habitList)
    }
}