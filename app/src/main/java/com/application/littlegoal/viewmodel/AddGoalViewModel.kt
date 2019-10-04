package com.application.littlegoal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.application.littlegoal.data.Goal
import com.application.littlegoal.data.GoalRepository
import kotlinx.coroutines.launch


class AddGoalViewModel internal constructor(
    private val goalRepository: GoalRepository
): ViewModel() {
    fun addGoal(goal: Goal) {
        viewModelScope.launch {
            goalRepository.createGoal(goal)
        }
    }
}
