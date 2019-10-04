package com.application.littlegoal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.littlegoal.data.GoalRepository

class AddGoalViewModelFactory(
    private val goalRepository: GoalRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddGoalViewModel(goalRepository) as T
    }
}