package com.application.littlegoal


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.application.littlegoal.data.GoalRepository
import com.application.littlegoal.databinding.FragmentGoalDetailsBinding
import com.application.littlegoal.utils.InjectorUtils
import com.application.littlegoal.viewmodel.GoalDetailsViewModel
import kotlin.concurrent.thread


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class GoalDetailsFragment : Fragment() {
    private val args: GoalDetailsFragmentArgs by navArgs()
    private val goalDetailsViewModel: GoalDetailsViewModel by viewModels {
        InjectorUtils.provideGoalDetailViewModelFactory(requireActivity(), args.goalId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGoalDetailsBinding>(
            inflater, R.layout.fragment_goal_details, container, false
        ).apply {
            viewModel = goalDetailsViewModel
            lifecycleOwner = this@GoalDetailsFragment
            buttonDelete.setOnClickListener {
                AlertDialog.Builder(context)
                    .setTitle("删除提示")
                    .setMessage("确定要删除目标吗？")
                    .setPositiveButton("是") { _, _ ->
                        thread {
                            goalDetailsViewModel.deleteGoal(args.goalId)
                        }
                        findNavController().navigate(GoalDetailsFragmentDirections.actionGoalDetailsFragmentToGoalListFragment())
                    }
                    .setNegativeButton("否", null)
                    .show()
            }
        }
        return binding.root
    }


}
