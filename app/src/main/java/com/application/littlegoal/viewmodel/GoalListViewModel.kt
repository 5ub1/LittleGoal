package com.application.littlegoal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.application.littlegoal.data.Goal
import com.application.littlegoal.data.GoalRepository

class GoalListViewModel internal constructor(
    goalRepository: GoalRepository
): ViewModel() {
    val goals: LiveData<List<Goal>> = goalRepository.getGoals()
}
