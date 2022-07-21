package com.example.androidbasics

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TaskDetailRepository @Inject constructor() : ITaskDetailRepository {

    override fun fetchUsersData(): Flow<Result<UserResponse>> = flow {
        try {
            val response = ClientProvider.create().getUsers()
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }

}