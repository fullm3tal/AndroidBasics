package com.example.androidbasics


class TaskDetailRepository {

    suspend fun getUsersData(): Result<UserRespnse> {
        return try {
            val response = CallbackHandler.processNetworkCall(ClientProvider.create().getUsers())
            Result.Success(response)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

}