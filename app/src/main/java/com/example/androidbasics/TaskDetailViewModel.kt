package com.example.androidbasics

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class TaskDetailViewModel @Inject constructor(private val repository: ITaskDetailRepository) : ViewModel() {
    val name = "Kishan"

    var job: Job? = null
    var number = 0

    private var mutableStateFlow: MutableStateFlow<TaskDetailUIState> = MutableStateFlow(
        TaskDetailUIState(
            taskName = "Book Keeping",
            taskDescription = "Get Books",
            taskPriority = "Medium",
            taskOwner = "Owner",
            taskCompletionDate = "12 January 2023"
        )
    )
    val stateFlow: StateFlow<TaskDetailUIState> = mutableStateFlow

    fun fetchStatus() {
        if(job?.isActive == true) {
            Log.v("TAG", "Cancelling job +${job}")
            job?.cancel()
        }
      job = viewModelScope.launch {
            repository.fetchUsersData().collect {
                when (it) {
                    is Result.Failure -> {
                        number++
                        updateNumber(number)
                    }
                    Result.Loading -> {
                        update(Tesla.PARENT)
                    }
                    is Result.Success -> {
                        update(Tesla.SUCCESS)
                    }
                }
            }
        }
        Log.v("TAG", "New job +${job}")
    }

    fun setOwnerName() {
        viewModelScope.launch {
            mutableStateFlow.update { uiState ->
                if (uiState.taskOwner == name) {
                    uiState.copy(taskOwner = "Ram lal", shouldShowCompletion = true)
                } else {
                    uiState.copy(taskOwner = name, shouldShowCompletion = false)
                }
            }
        }
    }

    val flow = flowOf(Tesla.SUCCESS, Tesla.FAILURE, Tesla.DECOMPOSE, Tesla.PARENT)

    fun update(value: Tesla) {
        mutableStateFlow.update {
            it.copy(taskOwner = value.toString())
        }
    }

    fun updateNumber(value: Int) {
        mutableStateFlow.update {
            it.copy(taskOwner = value.toString())
        }
    }
}


enum class Tesla {
    SUCCESS, FAILURE, DECOMPOSE, PARENT
}