package com.application.littlegoal.data

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class GoalRepository private constructor(
    private val goalDao: GoalDao
) {
    fun getGoals() = goalDao.getGoals()

    fun getGoal(goalId: Int) = goalDao.getGoal(goalId)

    suspend fun createGoal(goal: Goal){
        withContext(IO){
            goalDao.insertGoal(goal)
        }
    }

    fun deleteGoal(goalId: Int){
        goalDao.deleteGoal(goalId)
    }

    companion object {
        @Volatile
        private var instance: GoalRepository? = null

        fun getInstance(goalDao: GoalDao) =
            instance ?: synchronized(this) {
                instance
                    ?: GoalRepository(goalDao).also { instance = it }
            }
    }
}