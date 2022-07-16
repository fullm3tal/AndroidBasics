package com.example.androidbasics

import android.content.Context
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

object CallbackHandler {

    suspend fun <T> processNetworkCall(call: Call<T>): T =
        suspendCancellableCoroutine { continuation ->
            call.enqueue(object : Callback<T> {

                override fun onResponse(call: Call<T>, response: Response<T>) {
                        response.body()?.let {
                            continuation.resume(it)
                        }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }

}