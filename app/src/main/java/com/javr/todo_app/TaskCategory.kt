package com.javr.todo_app

sealed class TaskCategory {
    object Personal : TaskCategory()
    object Business : TaskCategory()
    object Other : TaskCategory()
}