package com.application.littlegoal

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.application.littlegoal.data.Goal
import com.application.littlegoal.databinding.AddGoalFragmentBinding
import com.application.littlegoal.ui.NiceImageView
import com.application.littlegoal.utils.FREQUENCY_ITEMS
import com.application.littlegoal.utils.FREQUENCY_MAP
import com.application.littlegoal.utils.InjectorUtils
import com.application.littlegoal.viewmodel.AddGoalViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.add_goal_fragment.*
import java.text.SimpleDateFormat
import java.util.*


class AddGoalFragment : Fragment() {

    private val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.CHINA)
    private lateinit var binding: AddGoalFragmentBinding

    companion object {
        fun newInstance() = AddGoalFragment()
    }

    private val viewModel: AddGoalViewModel by viewModels {
        InjectorUtils.provideAddGoalViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddGoalFragmentBinding.inflate(inflater, container, false)
        binding.addStartDate.text = sdf.format(Calendar.getInstance().time)
        binding.addEndDate.text = sdf.format(Calendar.getInstance().time)
        binding.startDateLayout.setOnClickListener(dateOnClickListener(binding.addStartDate))
        binding.endDateLayout.setOnClickListener(dateOnClickListener(binding.addEndDate))
        binding.addFrequency.apply {
            if(text.isNullOrEmpty()){
                text = FREQUENCY_ITEMS[0]
            }
            setOnClickListener {
                AlertDialog.Builder(context)
                    .setItems(FREQUENCY_ITEMS) { _, i ->
                        binding.addFrequency.text = FREQUENCY_ITEMS[i]
                    }.create().show()
            }
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add_save, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.add_goal_save -> {
                if(binding.addName.text.isNullOrEmpty()){
                    Snackbar.make(requireView(), "Name is empty", Snackbar.LENGTH_LONG).show()
                }
                val name = binding.addName.text.toString()
                val description = binding.addDescription.text.toString()
                val startDate = binding.addStartDate.text.toString()
                val endDate = binding.addEndDate.text.toString()
                var frequency = binding.addFrequency.text.toString()
                frequency = FREQUENCY_MAP[frequency].toString()
                val goal = Goal(
                    name = name,
                    description = description,
                    startDate = startDate,
                    endDate = endDate,
                    frequency = Integer.parseInt(frequency))
                viewModel.addGoal(goal)
                val direction = AddGoalFragmentDirections.actionAddGoalFragmentToGoalListFragment()
                findNavController().navigate(direction)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun dateOnClickListener(textView: TextView) : View.OnClickListener {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return View.OnClickListener {
            DatePickerDialog(requireContext(),
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, monthOfYear)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    textView.text = sdf.format(calendar.time)
                },
                year,
                month,
                day
            ).show()
        }
    }

    private fun imageOnClickListener(view: NiceImageView) : View.OnClickListener{
        return View.OnClickListener {

        }
    }
}
