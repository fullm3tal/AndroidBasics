package com.example.androidbasics

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.androidbasics.databinding.FragmentTaskDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TaskDetailFragment : Fragment() {

    companion object {
        fun newInstance() = TaskDetailFragment()
    }

    lateinit var binding : FragmentTaskDetailBinding

    private val viewModel: TaskListViewModel by activityViewModels()

    private val detailViewModel: TaskDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.v("Data", viewModel.data.toString())
        observeLiveData()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.fab1.setOnClickListener {
            detailViewModel.fetchStatus()
        }
        binding.fab2.setOnClickListener {
            detailViewModel.fetchStatus()
        }
    }

    private fun observeLiveData() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                detailViewModel.stateFlow.collect {
                    binding.uiState = it
                    if(it.shouldShowCompletion) {
                        binding.tvTaskCompletionDate.visibility = View.VISIBLE
                    } else {
                        binding.tvTaskCompletionDate.visibility = View.GONE
                    }
                }
            }
        }
    }
}