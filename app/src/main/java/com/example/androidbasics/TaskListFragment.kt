package com.example.androidbasics

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import kotlinx.android.synthetic.main.fragment_task_list.*

class TaskListFragment : Fragment() {

    private val viewModel: TaskListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data = "Hello"
        Log.v("Data", viewModel.data.toString())
        btnTaskDetail.setOnClickListener {
            parentFragmentManager.beginTransaction().loadFragment(TaskDetailFragment())
        }
    }

}