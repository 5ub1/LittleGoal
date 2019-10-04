package com.application.littlegoal.utils

import android.content.Context
import com.application.littlegoal.data.AppDatabase
import com.application.littlegoal.data.GoalRepository
import com.application.littlegoal.viewmodel.AddGoalViewModelFactory
import com.application.littlegoal.viewmodel.GoalDetailsViewModelFactory
import com.application.littlegoal.viewmodel.GoalListViewModelFactory

object InjectorUtils {
    private fun getGoalRepository(context: Context): GoalRepository {
        return GoalRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).goalDao())
    }

    fun provideGoalListViewModelFactory(
        context: Context
    ): GoalListViewModelFactory {
        val repository = getGoalRepository(context)
        return GoalListViewModelFactory(repository)
    }

    fun provideAddGoalViewModelFactory(
        context: Context
    ): AddGoalViewModelFactory {
        val repository = getGoalRepository(context)
        return AddGoalViewModelFactory(repository)
    }

    fun provideGoalDetailViewModelFactory(
        context: Context,
        goalId: Int
    ): GoalDetailsViewModelFactory {
        return GoalDetailsViewModelFactory(getGoalRepository(context), goalId)
    }
}