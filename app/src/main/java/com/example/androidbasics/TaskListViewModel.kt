package com.example.androidbasics

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor() : ViewModel() {

    var data: String? = null
}