package com.application.littlegoal.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GoalDao{
    @Query("SELECT * FROM goals ORDER BY id")
    fun getGoals(): LiveData<List<Goal>>

    @Query("SELECT * FROM goals WHERE id = :goalId")
    fun getGoal(goalId: Int): LiveData<Goal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(goals: List<Goal>)

    @Insert
    fun insertGoal(goal: Goal): Long

    @Query("DELETE FROM goals WHERE id = :goalId")
    fun deleteGoal(goalId: Int)
}