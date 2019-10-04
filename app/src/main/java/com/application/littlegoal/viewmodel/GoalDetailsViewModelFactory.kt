package com.application.littlegoal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.littlegoal.data.GoalRepository

class GoalDetailsViewModelFactory(
    private val goalRepository: GoalRepository,
    private val goalId: Int
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GoalDetailsViewModel(goalRepository, goalId) as T
    }
}