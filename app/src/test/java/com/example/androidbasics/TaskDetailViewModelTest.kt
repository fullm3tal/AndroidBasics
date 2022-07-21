package com.example.androidbasics

import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4



class TaskDetailViewModelTest{

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getData() = runTest {
        val dispatcher = UnconfinedTestDispatcher(testScheduler, "Main")
        try {
            Dispatchers.setMain(dispatcher)
            val repository = FakeRepository()
            val viewModel = TaskDetailViewModel(repository)
            assertEquals("Book Keeping", viewModel.stateFlow.value.taskName)
            viewModel.fetchStatus()
            repository.emit(Result.Loading)
            assertEquals("PARENT", viewModel.stateFlow.value.taskOwner)
        } finally {
            Dispatchers.resetMain()
        }
    }
}

class FakeRepository : ITaskDetailRepository {

    private val flow = MutableSharedFlow<Result<UserResponse>>()
    suspend fun emit(value: Result<UserResponse>) = flow.emit(value)
    override fun fetchUsersData(): Flow<Result<UserResponse>> = flow

}
