package com.eegrab.habittrackermvvm.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.eegrab.habittrackermvvm.data.models.Habit

@Dao
interface HabitDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHabit(habit: Habit)

    @Update
    suspend fun updateHabit(habit: Habit)

    @Delete
    suspend fun deleteHabit(habit: Habit)

    @Query("select * from habit_table order by id desc")
    fun getAllHabits(): LiveData<List<Habit>>

    @Query("delete from habit_table")
    suspend fun deleteAll()
}