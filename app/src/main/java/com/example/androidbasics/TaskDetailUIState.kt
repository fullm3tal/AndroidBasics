package com.example.androidbasics


data class TaskDetailUIState(
    val taskName: String? = null,
    val taskDescription: String? = null,
    val taskPriority: String? = null,
    val taskOwner: String? = null,
    val taskCompletionDate: String? = null,
    val shouldShowCompletion: Boolean = false
)