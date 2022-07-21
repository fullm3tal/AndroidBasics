package com.example.androidbasics

import kotlinx.coroutines.flow.Flow

interface ITaskDetailRepository {

    fun fetchUsersData() : Flow<Result<UserResponse>>

}
