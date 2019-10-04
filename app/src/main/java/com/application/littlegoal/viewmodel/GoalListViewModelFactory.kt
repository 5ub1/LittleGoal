package com.application.littlegoal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.littlegoal.data.GoalRepository

class GoalListViewModelFactory(
    private val goalRepository: GoalRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GoalListViewModel(goalRepository) as T
    }
}
