package com.application.littlegoal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.application.littlegoal.data.Goal
import com.application.littlegoal.data.GoalRepository

class GoalDetailsViewModel(
    private val goalRepository: GoalRepository,
    goalId: Int
) : ViewModel() {

    val goal: LiveData<Goal> = goalRepository.getGoal(goalId)

    fun deleteGoal(goalId: Int) {
        goalRepository.deleteGoal(goalId)
    }
}