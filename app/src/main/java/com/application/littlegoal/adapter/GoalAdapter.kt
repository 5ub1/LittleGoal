package com.application.littlegoal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.application.littlegoal.GoalListFragmentDirections
import com.application.littlegoal.data.Goal
import com.application.littlegoal.databinding.ListItemGoalBinding

class GoalAdapter : ListAdapter<Goal, GoalAdapter.ViewHolder>(GoalDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val goal = getItem(position)
        holder.apply {
            bind(createOnClickListener(goal.goalId), goal)
            itemView.tag = goal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemGoalBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    private fun createOnClickListener(goalId: Int): View.OnClickListener {
        return View.OnClickListener {
            val direction =
                GoalListFragmentDirections.actionGoalListFragmentToGoalDetailsFragment(goalId)
            it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(
        private val binding: ListItemGoalBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Goal) {
            binding.apply {
                clickListener = listener
                goal = item
                executePendingBindings()
            }
        }
    }
}

private class GoalDiffCallback : DiffUtil.ItemCallback<Goal>() {

    override fun areItemsTheSame(oldItem: Goal, newItem: Goal): Boolean {
        return oldItem.goalId == newItem.goalId
    }

    override fun areContentsTheSame(oldItem: Goal, newItem: Goal): Boolean {
        return oldItem == newItem
    }
}