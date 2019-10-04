package com.application.littlegoal


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.application.littlegoal.adapter.GoalAdapter
import com.application.littlegoal.databinding.FragmentGoalListBinding
import com.application.littlegoal.utils.InjectorUtils
import com.application.littlegoal.viewmodel.GoalListViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class GoalListFragment : Fragment() {

    private val viewModel: GoalListViewModel by viewModels {
        InjectorUtils.provideGoalListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGoalListBinding.inflate(inflater, container, false)
        context ?: return binding.root
        val adapter = GoalAdapter()
        binding.goalList.adapter = adapter
        subscribeUi(adapter, binding)
        binding.fab.setOnClickListener {
            val direction = GoalListFragmentDirections.actionGoalListFragmentToAddGoalFragment()
            it.findNavController().navigate(direction)
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun subscribeUi(adapter: GoalAdapter, binding: FragmentGoalListBinding) {
        viewModel.goals.observe(viewLifecycleOwner) { goals ->
            binding.hasGoals = !goals.isNullOrEmpty()
        }

        viewModel.goals.observe(viewLifecycleOwner) { result ->
            if (!result.isNullOrEmpty())
                adapter.submitList(result)
        }
    }

}
