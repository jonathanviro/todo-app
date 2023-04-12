package com.javr.todo_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.javr.todo_app.TaskCategory.*

class MainActivity : AppCompatActivity() {
    private val lstCategories = listOf(
        Business,
        Personal,
        Other
    )

    private val lstTask = mutableListOf(
        Task("Prueba Business", Business),
        Task("Prueba Persona", Personal),
        Task("Prueba Other", Other),
    )

    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var rvTask: RecyclerView
    private lateinit var tasksAdapter: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()
        initUI()
    }

    private fun initComponents() {
        rvCategories = findViewById(R.id.rvCategories)
        rvTask = findViewById(R.id.rvTasks)
    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(lstCategories)
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter

        tasksAdapter = TasksAdapter(lstTask)
        rvTask.layoutManager = LinearLayoutManager(this)
        rvTask.adapter = tasksAdapter
    }
}