package com.eegrab.habittrackermvvm.ui.fragments.createHabit

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eegrab.habittrackermvvm.R
import com.eegrab.habittrackermvvm.databinding.FragmentCreateHabitItemBinding
import com.eegrab.habittrackermvvm.ui.viewModels.HabitViewModel
import com.eegrab.habittrackermvvm.utils.Calculations
import kotlinx.android.synthetic.main.fragment_create_habit_item.*
import java.util.*


class CreateHabitItem : Fragment(R.layout.fragment_create_habit_item),
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
    private lateinit var binding: FragmentCreateHabitItemBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: is called")
        Toast.makeText(context, "onviewcreated is called", Toast.LENGTH_SHORT).show()
        binding = FragmentCreateHabitItemBinding.bind(view)
        habitViewModel = ViewModelProvider(this).get(HabitViewModel::class.java)

        binding.btnConfirm.setOnClickListener {
            //addHabitToDB()
        }

        pickDateAndTime()

        drawableSelected()

    }

    private fun drawableSelected() {
        binding.ivFastFoodSelected.setOnClickListener {
            iv_fastFoodSelected.isSelected = !iv_fastFoodSelected.isSelected
            drawableSelected = R.drawable.ic_fastfood

            iv_smokingSelected.isSelected = false
            iv_teaSelected.isSelected = false
        }

        binding.ivSmokingSelected.setOnClickListener {
            iv_smokingSelected.isSelected = !iv_smokingSelected.isSelected
            drawableSelected = R.drawable.ic_smoke

            iv_teaSelected.isSelected = false
            iv_fastFoodSelected.isSelected = false
        }

        binding.ivTeaSelected.setOnClickListener{
            binding.ivTeaSelected.isSelected = !binding.ivTeaSelected.isSelected
            drawableSelected = R.drawable.ic_tea

            iv_fastFoodSelected.isSelected = false
            iv_smokingSelected.isSelected = false
        }
    }

    private fun pickDateAndTime() {
        btn_pickDate.setOnClickListener {
            Log.d(TAG, "pickDate is clicked")
            getDateCalender()
            DatePickerDialog(requireContext(),this,year,month,day).show()
        }

        btn_pickTime.setOnClickListener {
            Log.d(TAG, "pickTime is clicked")
            getTimeCalender()
            TimePickerDialog(context,this, hour, minute, true).show()
        }
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        cleanTime = Calculations.cleanTime(hourOfDay,minute)
        binding.tvTimeSelected.text = "Time: $cleanTime"
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        cleanDate = Calculations.cleanDate(dayOfMonth,month,year)
        binding.tvDateSelected.text = "Date: $cleanDate"
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
}